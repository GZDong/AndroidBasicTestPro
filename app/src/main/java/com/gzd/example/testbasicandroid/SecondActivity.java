package com.gzd.example.testbasicandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends BaseActivity {

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(SecondActivity.this, NoToolBarActivity.class);
                startActivity(intent);
            }
        });

        String msgFromOther = getIntent().getStringExtra("token");
        TextView textFromOther = findViewById(R.id.text_msg_from_act1);
        String msgFromItem = getIntent().getStringExtra("extraMsg");
        if (msgFromItem == null){
            textFromOther.setText(msgFromOther);
        }else{
            textFromOther.setText(msgFromItem + "/" + msgFromOther);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("return_result","I return");
        setResult(RESULT_OK,intent);
        finish();
    }
}
