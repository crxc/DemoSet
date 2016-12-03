package com.example.demo;

import android.app.Application;
import android.content.Context;

/**
 * Created by crxc on 2016/12/2.
 */

public class App extends Application {
    private static App app;


    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }

    public static App getContext() {
        return app;
    }
}
