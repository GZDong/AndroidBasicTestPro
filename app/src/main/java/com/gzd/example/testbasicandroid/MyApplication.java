package com.gzd.example.testbasicandroid;

import android.app.Application;

import org.litepal.LitePal;

/**
 * Created by gzd on 2019/1/24 0024
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
