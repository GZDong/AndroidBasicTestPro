package com.gzd.example.testbasicandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gzd.example.testbasicandroid.R;
import com.gzd.example.testbasicandroid.pojo.TestData;

import java.util.ArrayList;

/**
 * Created by gzd on 2019/1/23 0023
 */
public class SecondFragment extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_2,container,false);
        return view;
    }
    public void refresh(String p1,String p2){
        TextView t1 = view.findViewById(R.id.text_frag_2_p1);
        TextView t2 = view.findViewById(R.id.text_frag_2_p2);
        t1.setText(p1);
        t2.setText(p2);
    }
}
