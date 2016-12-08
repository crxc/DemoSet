package com.example.demo.services;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.demo.BroadCastReceiver.MyReceiver_;

public class MonitorService extends Service {
    private MyReceiver_ myReceiver;
    private static final String TAG = "MonitorService";
    public MonitorService() {
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: 解绑服务");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        myReceiver = new MyReceiver_();
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        intentFilter.addAction("com.example.crxc");
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        IntentFilter intentFilter1=new IntentFilter();
        intentFilter1.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        intentFilter1.addAction(Intent.ACTION_MEDIA_MOUNTED);
        IntentFilter intentFilter2=new IntentFilter();
        intentFilter2.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter2.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter1.addDataScheme("file");
        intentFilter2.addDataScheme("package");
        registerReceiver(myReceiver,intentFilter);
        registerReceiver(myReceiver,intentFilter1);
        registerReceiver(myReceiver,intentFilter2);
        Log.d(TAG, "onCreate: 注册广播");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: 绑定服务");
        return new MyBinder();
    }
    public interface MonitorControl{
        void monitorSms(boolean b);
        void monitorMyBroadCast(boolean b);

        void monitorAppState(boolean b);

        void monitorSdCardState(boolean b);

        void monitorScreenState(boolean b);
    }
    class MyBinder extends Binder implements MonitorControl{

        @Override
        public void monitorSms(boolean b) {
            Log.d(TAG, "setMonitorSms: 开启短信监控服务");
            myReceiver.setMonitorSms(b);
        }

        @Override
        public void monitorMyBroadCast(boolean b) {
            Log.d(TAG, "setMonitorMyAction: 开启自定义广播监控服务");
            myReceiver.setMonitorMyAction(b);
        }

        @Override
        public void monitorAppState(boolean b) {
            Log.d(TAG, "setMonitorMyAction: 开启App状态监控监控服务");
            myReceiver.setMonitorAppState(b);
        }

        @Override
        public void monitorSdCardState(boolean b) {
            Log.d(TAG, "setMonitorMyAction: 开启Sd监控监控服务");
            myReceiver.setMonitorSdCardState(b);
        }

        @Override
        public void monitorScreenState(boolean b) {
            Log.d(TAG, "setMonitorMyAction: 开启屏幕监控服务");
            myReceiver.setMonitorScreenState(b);
        }
    }
}
