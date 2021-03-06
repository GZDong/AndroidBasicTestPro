package com.gzd.example.testbasicandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DialogActivity extends BaseActivity {

    public static void actionStart(Context context, String msg){
        Intent intent = new Intent(context,DialogActivity.class);
        intent.putExtra("msgForDialog",msg);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }
}
