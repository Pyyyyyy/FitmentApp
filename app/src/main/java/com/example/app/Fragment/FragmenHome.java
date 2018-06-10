package com.example.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.app.FitmentMaterial.FitmentMaterialActivity;
import com.example.app.QingBao.zhuangxiuzhongActivity;
import com.example.app.R;
import com.example.app.Worker.WorkerActivity;
import com.example.app.zhuangXiuQian.zhuangxiuqianActivity;
import com.example.app.zhuangXiuQian.SlideShowView;

import java.util.ArrayList;
import java.util.List;


public class FragmenHome extends Fragment {
    private SlideShowView mSlideShowView;
    private SlideShowView mSlideShowView02;


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
        Button btn2= (Button) getActivity().findViewById(R.id.button_qingbao);
        Button btn3= (Button) getActivity().findViewById(R.id.button_zhuangxiuhou);
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
                Intent intent = new Intent();
                intent.setClass(getActivity(), FitmentMaterialActivity.class);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button点击事件
                Intent intent = new Intent();
                intent.setClass(getActivity(), WorkerActivity.class);
                startActivity(intent);
            }
        });

    }
}




