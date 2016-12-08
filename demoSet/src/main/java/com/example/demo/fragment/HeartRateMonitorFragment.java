package com.example.demo.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.SystemClock;
import android.widget.ImageView;

import com.example.demo.R;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.Random;

/**
 * Created by crxc on 2016/12/5.
 */
@EFragment(R.layout.fragment_heart_rate_monitor)
public class HeartRateMonitorFragment extends Fragment {
    static {
        System.loadLibrary("heartRateMonitor-lib");
    }

    private boolean isMonitor=true;
    private int scale=40;
    @ViewById(R.id.image)
    ImageView imageView;

    @Click
    void button(){
        isMonitor=true;
        imageView.setDrawingCacheEnabled(true);
        Bitmap drawingCache = imageView.getDrawingCache();
        Bitmap bitmap=Bitmap.createBitmap(drawingCache.getWidth(),drawingCache.getHeight(),drawingCache.getConfig());
        Paint paint = new Paint();
        paint.setStrokeWidth(20);
        paint.setColor(Color.RED);
        int height = bitmap.getHeight();
        int width=bitmap.getWidth();
        int startX=0;
        int startY=height-0;
        Canvas canvas=new Canvas(bitmap);
        method(bitmap, paint, startX, startY, canvas,height,width);
    }
    @Click
    void button2(){
        isMonitor=false;
    }
    @Background
    void method(Bitmap bitmap, Paint paint, int startX, int startY, Canvas canvas,int height,int width) {
        int endX;
        int endY;
        for (int i = 0; isMonitor; i++) {
            endX=startX+scale;
            endY=getHeartRate(height);
            canvas.drawLine(startX,startY,endX,endY,paint);
            if(endX>=width-scale){
                Matrix matrix = new Matrix();
                matrix.setTranslate(-scale,0);
                Bitmap bitmap1=Bitmap.createBitmap(width,height,bitmap.getConfig());
                Canvas canvas1=new Canvas(bitmap1);
                canvas1.drawBitmap(bitmap,matrix,paint);
                canvas=canvas1;
//                canvas.setBitmap(bitmap1);
                bitmap=bitmap1;
            }else {
                startX=endX;
            }
            startY=endY;
            SystemClock.sleep(100);
            setImage(bitmap);
        }
    }
    @UiThread
    void setImage(Bitmap bitmap) {
        if(imageView!=null)
        imageView.setImageBitmap(bitmap);
    }

    private native int getCurrentHeartRate() ;
    private int getHeartRate(int height){
        Random random = new Random();
        int i = random.nextInt(80);
        return (height-(i+40)*10);
    }
}
