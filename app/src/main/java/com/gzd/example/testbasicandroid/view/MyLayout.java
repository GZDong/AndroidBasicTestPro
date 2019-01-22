package com.gzd.example.testbasicandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gzd.example.testbasicandroid.R;

/**
 * Created by Administrator on 2019/1/23 0023
 */
public class MyLayout extends LinearLayout {
    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_my,this);
        TextView textView = findViewById(R.id.text_layout_view);
        textView.setText("22222");
    }
}
