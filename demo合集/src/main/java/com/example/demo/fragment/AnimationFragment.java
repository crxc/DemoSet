package com.example.demo.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.demo.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.ViewById;

/**
 * Created by crxc on 2016/11/28.
 */
@EFragment(R.layout.fragment_animation)
public class AnimationFragment extends Fragment {
    @ViewById
    ImageView image;
    private Bitmap bitmap;

    @Click
    void button(){
        initBitmap();
        Animation animation= getAnimation(R.anim.translate_animation);
        startAnimation(animation);
    }
     @Click
    void button2(){
         initBitmap();
         Animation animation= getAnimation(R.anim.alpha_animation);
         startAnimation(animation);
     }
     @Click
    void button3(){
         initBitmap();
         Animation animation = getAnimation(R.anim.rotate_animation);
         startAnimation(animation);
     }

    @Click
    void button4(){
         initBitmap();
        Animation animation= getAnimation(R.anim.scale_animation);
        startAnimation(animation);
    }

    @Touch(R.id.image)
    void playAnimationSet(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_UP){
            Animation animation = getAnimation(R.anim.set_animation);
            animation.setRepeatMode(Animation.REVERSE);
            animation.setDuration(3000);
            image.startAnimation(animation);
        }
    }

    private Animation getAnimation(int scale_animation) {
        return AnimationUtils.loadAnimation(getActivity(), scale_animation);
    }

    private void initBitmap() {
        if(bitmap==null){
            image.setDrawingCacheEnabled(true);
            bitmap=image.getDrawingCache();
        }
    }
    private void startAnimation(Animation animation) {
        animation.setRepeatCount(3);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setDuration(2000);
        image.startAnimation(animation);
    }

}
