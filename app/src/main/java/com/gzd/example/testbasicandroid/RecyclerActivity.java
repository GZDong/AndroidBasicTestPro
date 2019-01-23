package com.gzd.example.testbasicandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gzd.example.testbasicandroid.pojo.TestData;
import com.gzd.example.testbasicandroid.tool.RecAdapter;

import java.util.ArrayList;

public class RecyclerActivity extends BaseActivity {

    public static void actionStart(Context context, String msg){
        Intent intent = new Intent(context,RecyclerActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        ArrayList<TestData> testData = new ArrayList<>();
        for (int i = 0;i <= 5; i++){
            testData.add(new TestData("d"+ i,"d2" + i));
        }

        RecyclerView recyclerView = findViewById(R.id.list_rec);
        RecAdapter adapter = new RecAdapter(testData);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        /*当数据发生变化时以及需要列表滑动时
        adapter.notifyItemInserted(testData.size()-1);
        recyclerView.scrollToPosition(testData.size()-1);*/
    }
}
