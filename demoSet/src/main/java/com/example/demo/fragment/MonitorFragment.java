package com.example.demo.fragment;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.example.demo.R;
import com.example.demo.services.MonitorService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

/**
 * Created by crxc on 2016/12/4.
 */
@EFragment(R.layout.fragment_monitor)
public class MonitorFragment extends Fragment {
    private static final String TAG = "MonitorFragment";
    private boolean isBind=false;
    private MonitorService.MonitorControl binder;
    private MyConn myConn;


    @CheckedChange(R.id.cb1)
    void monitorSms(boolean isChecked){
            binder.monitorSms(isChecked);
    }
     @CheckedChange(R.id.cb2)
    void monitorAppState(boolean isChecked){
            binder.monitorAppState(isChecked);
    }
    @CheckedChange(R.id.cb3)
    void monitorSdCardState(boolean isChecked){
            binder.monitorSdCardState(isChecked);
    }
 @CheckedChange(R.id.cb4)
    void monitorMyBroadCast(boolean isChecked){
            binder.monitorMyBroadCast(isChecked);
    }
 @CheckedChange(R.id.cb5)
    void monitorScreenState(boolean isChecked){
            binder.monitorScreenState(isChecked);
    }

    @Click
    void button(){
        Intent intent = new Intent("com.example.crxc");
        Bundle bundle = new Bundle();
        bundle.putString("news","同志们好");
        intent.putExtra("news",bundle);
        getActivity().sendBroadcast(intent);
        Log.d(TAG, "button: 发送自定义广播");
    }
    @AfterViews
    void bindService(){
        if (!isBind) {
            Intent intent = new Intent(getActivity(), MonitorService.class);
            getActivity().startService(intent);
            myConn = new MyConn();
            getActivity().bindService(intent,myConn, Context.BIND_AUTO_CREATE);
            Log.d(TAG, "开始绑定服务");
            isBind=true;
        }
    }
    class MyConn implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: 绑定服务获取binder成功");
            binder= (MonitorService.MonitorControl) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    public void onDestroy() {
        getActivity().unbindService(myConn);
        super.onDestroy();
    }
}
