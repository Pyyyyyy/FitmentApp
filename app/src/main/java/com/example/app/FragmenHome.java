package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;


import android.widget.Button;

import com.example.app.QingBao.zhuangxiuzhongActivity;


public class FragmenHome extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        Button btn = (Button) getActivity().findViewById(R.id.button_zhuangxiuqian);
        Button btn2= (Button) getActivity().findViewById(R.id.button_zhuangxiuhou);
        Button btn3= (Button) getActivity().findViewById(R.id.button_qingbao);
        Button btn4= (Button) getActivity().findViewById(R.id.button_zhuangxiucailiao);
        Button btn5= (Button) getActivity().findViewById(R.id.button_worker);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), zhuangxiuqianActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), zhuangxiuzhongActivity.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button点击事件
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button点击事件
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button点击事件
            }
        });

    }
}




