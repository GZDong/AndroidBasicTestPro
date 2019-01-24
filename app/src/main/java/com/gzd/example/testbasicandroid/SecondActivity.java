package com.gzd.example.testbasicandroid;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzd.example.testbasicandroid.OtherApplicationDemo.MainActivity;

public class SecondActivity extends BaseActivity {
    private ContentResolver resolver;
    public final static String AHTHORITY = "otheractivityprovider";
    public static void actionStart(Context context,String myParam){
        Intent intent =  new Intent(context,SecondActivity.class);
        intent.putExtra("extraMsg",myParam);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        resolver = getContentResolver();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(SecondActivity.this, NoToolBarActivity.class);
                startActivity(intent);
            }
        });

        String msgFromOther = getIntent().getStringExtra("token");
        final TextView textFromOther = findViewById(R.id.text_msg_from_act1);
        String msgFromItem = getIntent().getStringExtra("extraMsg");
        if (msgFromItem == null){
            textFromOther.setText(msgFromOther);
        }else{
            textFromOther.setText(msgFromItem + "/" + msgFromOther);
        }

        Button btnGoToOtherApp = findViewById(R.id.btn_go_to_other_application);
        btnGoToOtherApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnAddToOther = findViewById(R.id.btn_add_person_to_other_app);
        Button btnDeleteToOther = findViewById(R.id.btn_delete_from_other_app);
        Button btnUpdateToOther = findViewById(R.id.btn_update_other_app);
        Button btnGetToOther = findViewById(R.id.btn_get_from_other_app);
        TextView textOther = findViewById(R.id.text_other_app);
        btnAddToOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://" + AHTHORITY + "/" + "OtherPerson");
                ContentValues values = new ContentValues();
                values.put("name","lbj");
                values.put("age",34);
                resolver.insert(uri,values);
            }
        });
        btnDeleteToOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://" + AHTHORITY + "/" + "OtherPerson/name");
                resolver.delete(uri,"name = ?",new String[]{"lbj"});
            }
        });
        btnUpdateToOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://" + AHTHORITY + "/" + "OtherPerson/name");
                ContentValues values = new ContentValues();
                values.put("age",90);
                resolver.update(uri,values,"name = ?",new String[]{"lbj"});
            }
        });

/*
        Uri uri = Uri.parse("content://" + AHTHORITY + "/" + "OtherPerson");
        Cursor cursor =  resolver.query(uri,null,null,null,null);
        StringBuilder str = new StringBuilder();
        if (cursor!=null){
            while (cursor.moveToNext()){
                str.append(cursor.getString(cursor.getColumnIndex("name")));
                str.append("-");
                str.append(cursor.getString(cursor.getColumnIndex("age")));
                str.append("\n");
            }
        }
        textFromOther.setText(str.toString());
*/

        btnGetToOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://" + AHTHORITY + "/" + "OtherPerson");
                Cursor cursor =  resolver.query(uri,null,null,null,null);
                StringBuilder str = new StringBuilder();
                if (cursor!=null){
                    while (cursor.moveToNext()){
                        str.append(cursor.getString(cursor.getColumnIndex("name")));
                        str.append("-");
                        str.append(cursor.getString(cursor.getColumnIndex("age")));
                        str.append("\n");
                    }
                }
                textFromOther.setText(str.toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("return_result","I return");
        setResult(RESULT_OK,intent);
        finish();
    }
}
