package com.gzd.example.testbasicandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gzd.example.testbasicandroid.fragment.SecondFragment;

public class Fragment2Activity extends BaseActivity {
    public static void actionStart(Context context, String p1,String p2){
        Intent intent = new Intent(context,Fragment2Activity.class);
        intent.putExtra("p1",p1);
        intent.putExtra("p2",p2);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment2);

        String p1 = getIntent().getStringExtra("p1");
        String p2 = getIntent().getStringExtra("p2");

        SecondFragment fragment = (SecondFragment)getSupportFragmentManager().findFragmentById(R.id.frag_2);
        fragment.refresh(p1,p2);
    }
}
