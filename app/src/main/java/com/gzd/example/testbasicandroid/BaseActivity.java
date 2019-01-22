package com.gzd.example.testbasicandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gzd.example.testbasicandroid.tool.ActivityController;

/**
 * Created by Administrator on 2019/1/22 0022
 */
public class BaseActivity extends AppCompatActivity {
    public static String TAG = "BaseActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + getClass().getSimpleName());
        ActivityController.addAct(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.deleteAct(this);
    }
}
