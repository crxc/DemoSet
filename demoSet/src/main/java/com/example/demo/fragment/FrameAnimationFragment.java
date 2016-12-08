package com.example.demo.fragment;

import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.demo.R;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by crxc on 2016/12/5.
 */
@EFragment(R.layout.fragment_frame_animation_fragment)
public class FrameAnimationFragment extends Fragment {
    @ViewById(R.id.image)
    ImageView image;
    @Click
    void button(){
        startFrameAnimation();
    }
    @Background
    void startFrameAnimation() {
        AnimationDrawable drawable= (AnimationDrawable) getResources().getDrawable(R.drawable.animation_list);
        setSrc(drawable);
        drawable.start();
    }
    @UiThread
    void setSrc(AnimationDrawable drawable) {
        image.setImageDrawable(drawable);
    }

}
