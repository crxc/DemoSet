package com.example.demo.BroadCastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.EReceiver;
import org.androidannotations.annotations.ReceiverAction;

@EReceiver
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    public boolean isMonitorSms() {
        return monitorSms;
    }

    public void setMonitorSms(boolean monitorSms) {

        this.monitorSms = monitorSms;
    }

    public boolean isMonitorMyAction() {
        return monitorMyAction;
    }

    public void setMonitorMyAction(boolean monitorMyAction) {
        this.monitorMyAction = monitorMyAction;

    }

    private boolean monitorSms=false;
    private boolean monitorMyAction=false;
    private boolean monitorAppState=false;
    private boolean monitorScreenState=false;

    public boolean isMonitorSdCardState() {
        return monitorSdCardState;
    }

    public void setMonitorSdCardState(boolean monitorSdCardState) {
        this.monitorSdCardState = monitorSdCardState;
    }

    public boolean isMonitorScreenState() {
        return monitorScreenState;
    }

    public void setMonitorScreenState(boolean monitorScreenState) {
        this.monitorScreenState = monitorScreenState;
    }

    public boolean isMonitorAppState() {
        return monitorAppState;
    }

    public void setMonitorAppState(boolean monitorAppState) {
        this.monitorAppState = monitorAppState;
    }

    private boolean monitorSdCardState=false;

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }
    @ReceiverAction(actions = "android.provider.Telephony.SMS_RECEIVED")
    void SmsAction(Intent intent,Context context){
        if(!monitorSms)return;
        Object[] objects= (Object[]) intent.getExtras().get("pdus");
        for (int i=0;i<objects.length;i++) {
            Object object=objects[i];
            SmsMessage smsMessage=SmsMessage.createFromPdu((byte[]) object);
            String messageBody = smsMessage.getMessageBody();
            String address = smsMessage.getOriginatingAddress();
            Log.d(TAG, "SmsAction: 监测到一条短信，来自"+address+"内容为："+messageBody);
        }
    }
    @ReceiverAction(actions = "com.example.crxc")
    void myAction(Intent intent){
        if(!monitorMyAction)return;
        Bundle news = intent.getBundleExtra("news");
        String newsString = news.getString("news");
        Log.d(TAG, "myAction: 收到一条自定义广播::"+newsString);
    }
    @ReceiverAction(actions = {Intent.ACTION_PACKAGE_ADDED,Intent.ACTION_PACKAGE_REMOVED})
    void AppStateAction(Intent intent){
        if(!monitorAppState)return;
        switch (intent.getAction()) {
            case Intent.ACTION_PACKAGE_ADDED:
                Log.d(TAG, "AppStateAction: 有应用被安装了");
                break;
            case Intent.ACTION_PACKAGE_REMOVED:
                Log.d(TAG, "AppStateAction: 有应用被卸载了");
                break;
        }
    }
    @ReceiverAction(actions ={Intent.ACTION_MEDIA_UNMOUNTED,Intent.ACTION_MEDIA_MOUNTED})
    void SdCardAction(Intent intent){
        if(!monitorSdCardState)return;
        switch (intent.getAction()) {
            case Intent.ACTION_MEDIA_MOUNTED:
                Log.d(TAG, "SdCardAction: sd卡被挂载");
                break;
            case Intent.ACTION_MEDIA_UNMOUNTED:
                Log.d(TAG, "SdCardAction: sd卡被移除");
                break;
        }
    }
    @ReceiverAction(actions = {Intent.ACTION_SCREEN_ON,Intent.ACTION_SCREEN_OFF})
    void screenAction(Intent intent){
        if(!monitorScreenState)return;
            switch (intent.getAction()) {
                case Intent.ACTION_SCREEN_ON:
                    Log.d(TAG, "screenAction: 屏幕被打开");
                    break;
                case Intent.ACTION_SCREEN_OFF:
                    Log.d(TAG, "screenAction: 屏幕被关闭");
                    break;
            }
    }
}
