package com.gzd.example.testbasicandroid.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private MyBinder binder = new MyBinder();
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("service", "onCreate: " + "Service" );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("service", "onStartCommand: " + intent.getStringExtra("data"));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("service", "onDestroy: " + "service was over");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("service", "onBind: " + intent.getStringExtra("data"));
        return binder;
    }

    public class MyBinder extends Binder{

        public void startDownload(){
            Log.e("service", "startDownload: " + "startDownload..." );
            //在这里做工作
        }

        public int getProgress(){
            Log.e("service", "getProgress: " + "getProgress..." );
            return 1;
        }
    }
}
