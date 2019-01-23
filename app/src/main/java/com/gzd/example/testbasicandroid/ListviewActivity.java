package com.gzd.example.testbasicandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gzd.example.testbasicandroid.pojo.TestData;
import com.gzd.example.testbasicandroid.tool.ListviewAdapter;

import java.util.ArrayList;

public class ListviewActivity extends BaseActivity {

    public static void actionStart(Context context, String msg){
        Intent intent = new Intent(context,ListviewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ArrayList<TestData> testData = new ArrayList<>();
        for (int i = 0;i <= 5; i++){
            testData.add(new TestData("d1","d2"));
        }
        ListviewAdapter listviewAdapter = new ListviewAdapter(ListviewActivity.this,R.layout.item_listview,testData);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(listviewAdapter);
    }
}
