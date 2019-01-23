package com.gzd.example.testbasicandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gzd.example.testbasicandroid.tool.ActivityController;

/**
 * Created by Administrator on 2019/1/22 0022
 */
public class BaseActivity extends AppCompatActivity {
    public static String TAG = "BaseActivity";
    private BroadcastReceiver mReceiver;
    LocalBroadcastManager manager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + getClass().getSimpleName());
        ActivityController.addAct(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager = LocalBroadcastManager.getInstance(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("local broadcast");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
                builder.setTitle("close all");
                builder.setIcon(R.drawable.ic_launcher_foreground);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityController.finishAll();
                    }
                });
                builder.show();
            }
        };
        manager.registerReceiver(mReceiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterReceiver(mReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.deleteAct(this);
    }
}
