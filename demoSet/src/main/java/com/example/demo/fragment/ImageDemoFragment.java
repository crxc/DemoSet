package com.example.demo.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.example.demo.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by crxc on 2016/11/27.
 */
@EFragment(R.layout.fragment_image_demo)
public class ImageDemoFragment extends Fragment {
    @ViewById(R.id.img)
    ImageView img;
    private Bitmap bitmap;

    @Click(R.id.btn_expand)
    void expand(){
        initBitmap();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        float scaleX=(float) widthPixels/(float) width;
        float scaleY=(float)heightPixels/(float)height;
        float scale=scaleX>scaleY?scaleY:scaleX;
        Matrix matrix = new Matrix();
        matrix.postScale(scale,scale);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        img.setImageBitmap(bitmap);
    }

    private void initBitmap() {
        if(bitmap==null){
            img.setDrawingCacheEnabled(true);
            bitmap=img.getDrawingCache();
        }
    }

    @Click(R.id.btn_shrink)
    void shrink(){
        initBitmap();
        Matrix matrix = new Matrix();
        matrix.postScale(0.9f,0.9f);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        img.setImageBitmap(bitmap);
    }
    @Click
    void button(){
        initBitmap();
        Matrix matrix = new Matrix();
        matrix.setRotate(30,bitmap.getWidth()/2,bitmap.getHeight()/2);
        imageTransform(matrix);
    }

    private void imageTransform(Matrix matrix) {
        Bitmap copyBitmap=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        Canvas canvas = new Canvas(copyBitmap);
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap,matrix,paint);
        img.setImageBitmap(copyBitmap);
        bitmap=copyBitmap;
    }

    @Click
    void button2(){
        initBitmap();
        Matrix matrix = new Matrix();
        matrix.setTranslate(100,100);
        imageTransform(matrix);
    }
    @Click
    void button3(){
        initBitmap();
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f,0.5f);
        imageTransform(matrix);
    }
    @Click
    void button4(){
        initBitmap();
        Matrix matrix = new Matrix();
        matrix.setScale(-1,1);
        matrix.postTranslate(bitmap.getWidth(),0);
        imageTransform(matrix);
    }
}
