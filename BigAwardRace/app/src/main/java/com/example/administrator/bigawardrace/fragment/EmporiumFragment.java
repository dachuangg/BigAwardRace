package com.example.administrator.bigawardrace.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.bigawardrace.R;

public class EmporiumFragment extends Fragment {

    @Nullable//该注解表示的是是否可以传入空值null
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emporium,null);
        findView(view);
        return view;
    }

    //初始化控件
    private void findView(View view) {

    }
}
