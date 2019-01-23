package com.gzd.example.testbasicandroid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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

        final EditText editText = findViewById(R.id.edit_file);
        editText.setText(readFile());
        Button btnFile = findViewById(R.id.btn_save_file);
        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editText.getText().toString())){
                    String content = editText.getText().toString();
                    save(content);
                }
            }
        });
        final EditText editXML = findViewById(R.id.edit_xml);
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        if (!TextUtils.isEmpty(sharedPreferences.getString("content"," nothing "))){
            editXML.setText(sharedPreferences.getString("content","noting"));
        }
        Button btnXML = findViewById(R.id.btn_save_shared_preference_xml);
        btnXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editXML.getText().toString())){
                    SharedPreferences preferences1 = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences preferences2 = getSharedPreferences("myXML",Context.MODE_PRIVATE);
                    SharedPreferences preferences3 = PreferenceManager.getDefaultSharedPreferences(NoToolBarActivity.this);
                    SharedPreferences.Editor editor1 = preferences1.edit();
                    editor1.putString("content",editXML.getText().toString());
                    editor1.apply();
                    SharedPreferences.Editor editor2 = preferences2.edit();
                    editor2.putString("content",editXML.getText().toString());
                    editor2.apply();
                    SharedPreferences.Editor editor3 = preferences3.edit();
                    editor3.putString("content",editXML.getText().toString());
                    editor3.apply();
                }
            }
        });
    }

    public void save(String content){
        FileOutputStream outputStream = null;
        BufferedWriter writer = null;
        try {
            outputStream = openFileOutput("testFile",Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (writer != null){   //finally无论有无异常都会执行，所以writer可能会为空的
                    writer.close();
                }
               // outputStream.close();  writer会关闭流，同事清空缓存
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readFile(){
        StringBuilder content = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = openFileInput("testFile");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while (reader.read()!= -1){
                content.append(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (reader!= null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content.toString();
    }
}
