package com.example.app.main;

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
            }
        });
        /**
        * 轮播图
         */
        List<Integer> imageUris=new ArrayList<Integer>();
        imageUris.add(R.drawable.one);
        imageUris.add(R.drawable.two);
        imageUris.add(R.drawable.three);
        /**
         * 获取控件
         */
        mSlideShowView=(SlideShowView) getActivity().findViewById(R.id.slideshowView);
        /**
         * 为控件设置图片
         */
        mSlideShowView.setImageUris(imageUris);
        /**
         * 轮播图2
         */
        List<Integer> imageUris01=new ArrayList<Integer>();
        imageUris01.add(R.drawable.recommend01);
        imageUris01.add(R.drawable.recommend02);
        imageUris01.add(R.drawable.recommend03);
        /**
         * 获取控件
         */
        mSlideShowView02=(SlideShowView) getActivity().findViewById(R.id.slideshowView01);
        /**
         * 为控件设置图片
         */
        mSlideShowView02.setImageUris(imageUris01);

    }
}




