package com.gzd.example.testbasicandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gzd.example.testbasicandroid.R;
import com.gzd.example.testbasicandroid.pojo.TestData;
import com.gzd.example.testbasicandroid.tool.RecAdapter;
import com.gzd.example.testbasicandroid.tool.RecFrgAdapter;

import java.util.ArrayList;

/**
 * Created by gzd on 2019/1/23 0023
 */
public class FirstFragment extends Fragment {
    RecFrgAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_1,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.list_rec_frag_1);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        ArrayList<TestData> testData = new ArrayList<>();
        for (int i = 0;i <= 5; i++){
            testData.add(new TestData("d"+ i,"d2" + i));
        }


        if (getActivity().findViewById(R.id.frag_2) == null){
            adapter = new RecFrgAdapter(testData,false,getFragmentManager());
            Log.e("tt", "onCreated: 11111");
        }else {
            adapter = new RecFrgAdapter(testData,true,getFragmentManager());
            Log.e("tt", "onCreated: 22222");
        }

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        return view;
    }

    //确保Activity已经创建完成，否则就是找不到它的layout
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.frag_2) == null){
            adapter.setTwo(false);
            Log.e("tt", "onActivityCreated: 11111");
        }else {
            adapter.setTwo(true);
            Log.e("tt", "onActivityCreated: 22222");
        }
    }
}
