package com.gzd.example.testbasicandroid.OtherApplicationDemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzd.example.testbasicandroid.MyApplication;
import com.gzd.example.testbasicandroid.R;
import com.gzd.example.testbasicandroid.tool.MyThread;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase database ;
    TextView textMsg;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    textMsg.setText("msg from handler");
                break;
                default:
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_other);

        database = MyApplication.getDatabase();
        Button btnAdd = findViewById(R.id.btn_add_other);
        Button btnDelete = findViewById(R.id.btn_delete_other);
        Button btnGet = findViewById(R.id.btn_get_other);
        Button btnUpdate = findViewById(R.id.btn_update_other);
        final TextView textOther = findViewById(R.id.text_other);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.execSQL("insert into OtherPerson values(?,?)",new String[]{"gzd_other","52"});
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.execSQL("delete from OtherPerson where name = ?",new String[]{"gzd_other"});
            }
        });
        StringBuilder builder = new StringBuilder();
        Cursor cursor = database.rawQuery("select * from OtherPerson",null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                builder.append(cursor.getString(cursor.getColumnIndex("name")));
                builder.append("-");
                builder.append(cursor.getInt(cursor.getColumnIndex("age")));
            }
            textOther.setText(builder.toString());
        }
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder builder = new StringBuilder();
                Cursor cursor = database.rawQuery("select * from OtherPerson",null);
                if (cursor!=null){
                    while (cursor.moveToNext()){
                        builder.append(cursor.getString(cursor.getColumnIndex("name")));
                        builder.append("-");
                        builder.append(cursor.getInt(cursor.getColumnIndex("age")));
                    }
                    textOther.setText(builder.toString());
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("age",152);
                database.update("OtherPerson",values,"name = ?",new String[]{"gzd_other"});
            }
        });

        Button btnSendBroadcast = findViewById(R.id.btn_send_broadcast_to_main);
        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("goToMainUI");
                intent.putExtra("content","number is : "+Math.random());
                sendBroadcast(intent);
            }
        });

        Button btnPic = findViewById(R.id.btn_picture);
        Button btnVideo = findViewById(R.id.btn_video);
        Button btnPlayer = findViewById(R.id.btn_media_player);
        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PictureActivity.class));
            }
        });
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,VideoActivity.class));
            }
        });
        btnPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PlayerActivity.class));
            }
        });

        textMsg = findViewById(R.id.text_thread);

        Button btnSendCallback = findViewById(R.id.btn_send_msg_with_callback);
        btnSendCallback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread myThread = new MyThread();
                myThread.setCallBack(new MyThread.CallBack() {
                    @Override
                    public void handleMsg(String string) {
                        textMsg.setText(string);
                    }
                });
                myThread.start();
            }
        });


        Button btnSendHandler = findViewById(R.id.btn_send_msg_with_handler);
        btnSendHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }
}
