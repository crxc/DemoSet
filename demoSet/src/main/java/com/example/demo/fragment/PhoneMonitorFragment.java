package com.example.demo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.demo.MainActivity;
import com.example.demo.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

/**
 * Created by crxc on 2016/12/5.
 */
@EFragment(R.layout.fragment_phone_monitor)
public class PhoneMonitorFragment extends Fragment {
    Activity activity;
//    private boolean isSelectedPath=false;
    GetPathAble getPathAble;
    private String path;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        activity=getActivity();
        try {
            getPathAble= (GetPathAble) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+"must implement GetPathAble");
        }
    }

    @Click
    void button(){
            Toast.makeText(getActivity(), "先选择一个录音保存地址", Toast.LENGTH_SHORT).show();
            getPathAble.getPath();
    }
    @Click
    void button2(){

    }
    @Click
    void button3(){

    }
    @Click
    void button4(){

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public interface GetPathAble{
        void getPath();
    }
}
