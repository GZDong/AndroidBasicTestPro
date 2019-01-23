package com.gzd.example.testbasicandroid.tool;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gzd.example.testbasicandroid.R;
import com.gzd.example.testbasicandroid.pojo.TestData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gzd on 2019/1/23 0023
 */
//指定适配器为哪种类型的数据适配，以viewholder为一个基本操作单元
public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHodler> {
    ArrayList<TestData> data;
    public RecAdapter( ArrayList<TestData> datas) {
        data = datas;
    }

    //利用viewGroup去找到context
    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyViewHodler myViewHodler = new MyViewHodler(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rec,viewGroup,false));
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler viewHolder, int i) {
        viewHolder.text.setText(data.get(i).getTestParam2());
        viewHolder.btn.setText(data.get(i).getTestParam1());
    }

    //这个方法居然是必须要写的，它告诉RecView有多少个子项 0 0！
    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MyViewHodler extends RecyclerView.ViewHolder{
        Button btn;
        TextView text;
        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn_item);
            text = itemView.findViewById(R.id.text_item);
        }
    }
}
