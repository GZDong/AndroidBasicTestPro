package com.gzd.example.testbasicandroid.OtherApplicationDemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzd.example.testbasicandroid.MyApplication;
import com.gzd.example.testbasicandroid.R;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase database ;
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
    }
}
