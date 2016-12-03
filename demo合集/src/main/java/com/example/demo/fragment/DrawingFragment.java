package com.example.demo.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.demo.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.ViewById;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by crxc on 2016/11/27.
 */
@EFragment(R.layout.fragment_drawing)
public class DrawingFragment extends Fragment {
    private Bitmap mBitmap;
    int startX=0;
    int startY=0;
    @ViewById(R.id.image)
    ImageView mImageView;
    private Paint paint;
    @Click
    void button(){
        initPaint();
        paint.setColor(Color.RED);
    }

    private void initPaint() {
        if(paint==null){
            paint=new Paint();
        }
    }

    @Click
    void button2(){
        initPaint();
        paint.setColor(Color.GRAY);
    }
    @Click
    void button3(){
        initPaint();
        paint.setStrokeWidth(20);
    }
    @Click
    void button4(){
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Random random = new Random();
        FileOutputStream outputStream = null;
        try {
            if(mBitmap==null){
                Toast.makeText(getActivity(), "请先作画", Toast.LENGTH_SHORT).show();
                return;
            }
            outputStream = new FileOutputStream(path + "/Download/" + random.nextInt(10000)+".jpg");
            boolean b = mBitmap.compress(Bitmap.CompressFormat.JPEG, 0, outputStream);
            if(b){
                Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    @Touch(R.id.image)
    void draw(MotionEvent event, final View v){

        if(mBitmap==null){
            mImageView.setDrawingCacheEnabled(true);
            mBitmap=mImageView.getDrawingCache();
        }
        Bitmap copyBitmap=Bitmap.createBitmap(mBitmap.getWidth(),mBitmap.getHeight(),mBitmap.getConfig());
        Canvas canvas = new Canvas(copyBitmap);
        initPaint();
        canvas.drawBitmap(mBitmap,new Matrix(),paint);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = (int)event.getX();
                startY = (int)event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int endX=(int)event.getX();
                int endY=(int)event.getY();
                canvas.drawLine(startX,startY,endX,endY,paint);
                mImageView.setImageBitmap(copyBitmap);
                mBitmap=copyBitmap;
                startX=endX;
                startY=endY;
                break;

        }
    }
}
