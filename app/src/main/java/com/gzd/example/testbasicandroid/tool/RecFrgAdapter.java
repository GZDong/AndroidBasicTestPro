package com.gzd.example.testbasicandroid.tool;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gzd.example.testbasicandroid.Fragment2Activity;
import com.gzd.example.testbasicandroid.R;
import com.gzd.example.testbasicandroid.fragment.FirstFragment;
import com.gzd.example.testbasicandroid.fragment.SecondFragment;
import com.gzd.example.testbasicandroid.pojo.TestData;

import java.util.ArrayList;

/**
 * Created by gzd on 2019/1/23 0023
 */
public class RecFrgAdapter extends RecyclerView.Adapter<RecFrgAdapter.FragViewHolder> {
    ArrayList<TestData> data;
    boolean isTwo;
    FragmentManager manager;

    public RecFrgAdapter(ArrayList<TestData> data, boolean isTwo, FragmentManager manager) {
        this.data = data;
        this.isTwo = isTwo;
        this.manager = manager;
    }

    static class FragViewHolder extends RecyclerView.ViewHolder {
        Button btn;
        TextView text;
        public FragViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn_item);
            text = itemView.findViewById(R.id.text_item);
        }
    }

    @NonNull
    @Override
    public FragViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rec,viewGroup,false);
        final FragViewHolder viewHolder = new FragViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestData da = data.get(viewHolder.getAdapterPosition());
                if (isTwo){
                    SecondFragment fragment = (SecondFragment) manager.findFragmentById(R.id.frag_2);
                    fragment.refresh(da.getTestParam1(),da.getTestParam2());
                }else {
                    FirstFragment fragment = (FirstFragment) manager.findFragmentById(R.id.frag_1);
                    Fragment2Activity.actionStart(fragment.getActivity(),da.getTestParam1(),da.getTestParam2());
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FragViewHolder fragViewHolder, int i) {
        fragViewHolder.btn.setText(data.get(i).getTestParam1());
        fragViewHolder.text.setText(data.get(i).getTestParam2());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setTwo(boolean two) {
        isTwo = two;
    }
}
