package com.example.demo.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.demo.App;
import com.example.demo.R;
import com.mt.mtxx.image.JNI;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by crxc on 2016/12/5.
 */
@EFragment(R.layout.fragment_jni)
public class JniFragment extends Fragment {
    static {
        System.loadLibrary("mtimage-jni");
    }
    @ViewById(R.id.img)
    ImageView imageView;
    @Click
    void button(){
        imageView.setDrawingCacheEnabled(true);
        Bitmap drawingCache = imageView.getDrawingCache();
        int width = drawingCache.getWidth();
        int height = drawingCache.getHeight();
        Bitmap bitmap=Bitmap.createBitmap(width,height,drawingCache.getConfig());
        JNI jni=new JNI();
        int[] ints = new int[width * height];
        bitmap.getPixels(ints,0,width,0,0,width,height);
        jni.StyleCinnamon(ints,width,height);
        bitmap.setPixels(ints,0,width,0,0,width,height);
        imageView.setImageBitmap(bitmap);
    }
}
