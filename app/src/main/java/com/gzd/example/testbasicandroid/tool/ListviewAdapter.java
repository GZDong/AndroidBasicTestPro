package com.gzd.example.testbasicandroid.tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.gzd.example.testbasicandroid.R;
import com.gzd.example.testbasicandroid.pojo.TestData;

import java.util.List;

/**
 * Created by Administrator on 2019/1/23 0023
 */
//适配器模式
public class ListviewAdapter extends ArrayAdapter<TestData> {
    private int resId;
    public ListviewAdapter(Context context, int resource,List<TestData> objects) {
        super(context, resource, objects);
        resId = resource;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        View view;
        ViewHolder vh;
        TestData data = getItem(position);
        //利用缓存减少加载消耗
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resId,parent,false);
            vh = new ViewHolder();
            vh.btn = view.findViewById(R.id.btn_item);
            vh.text = view.findViewById(R.id.text_item);

            view.setTag(vh);
        }else{
            view = convertView;
            vh = (ViewHolder) convertView.getTag();
        }
        vh.btn.setText(data.getTestParam1());
        vh.text.setText(data.getTestParam2());
        return view;
    }
    class ViewHolder{
        Button btn;
        TextView text;
    }
}
