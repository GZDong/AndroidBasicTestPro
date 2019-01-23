package com.gzd.example.testbasicandroid;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class NoToolBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_tool_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }

        Button btn1 = findViewById(R.id.send_code_intent);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("code broadcast");
                sendBroadcast(intent);
            }
        });

        Button btn2 = findViewById(R.id.send_manifest_intent);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("manifest broadcast");
                //同一个包下
                intent.setComponent(new ComponentName("com.gzd.example.testbasicandroid.tool","com.gzd.example.testbasicandroid.tool.ManifestReceiver"));
                sendBroadcast(intent);
            }
        });
        Button btn3 = findViewById(R.id.send_manifest_intent_with_order);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("order broadcast");
                intent.putExtra("order broadcast", "ooooorder msg");
                sendOrderedBroadcast(intent,null);
            }
        });
        Button btn4 = findViewById(R.id.send_local_intent);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalBroadcastManager manager = LocalBroadcastManager.getInstance(NoToolBarActivity.this);
                Intent intent = new Intent("local broadcast");
                manager.sendBroadcast(intent);
            }
        });
    }

}
