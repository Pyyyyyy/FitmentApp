package com.example.app.QingBao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import android.widget.Button;

import android.widget.ImageView;

import com.example.app.QingBao.dengju.dengjucailiao;
import com.example.app.QingBao.dengju.dengjuliucheng;
import com.example.app.QingBao.dengju.dengjuzhuyishixiang;
import com.example.app.QingBao.nimu.nimucailiao;
import com.example.app.QingBao.nimu.nimuliucheng;
import com.example.app.QingBao.nimu.nimuzhuyishixiang;
import com.example.app.R;

/**
 * Created by 1 on 2018/1/6.
 */

public class dengjuActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dengju_layout);

        ImageView imageView01 = (ImageView) findViewById(R.id.lostandfound_back_imageview);
        imageView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dengjuActivity.this,zhuangxiuzhongActivity.class);
                startActivity(intent);
            }
        });


        Button button1 = (Button) findViewById(R.id.button1) ;
        Button button2 = (Button) findViewById(R.id.button2) ;
        Button button3 = (Button) findViewById(R.id.button3) ;
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(dengjuActivity.this,dengjuliucheng.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(dengjuActivity.this,dengjucailiao.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(dengjuActivity.this,dengjuzhuyishixiang.class);
                startActivity(intent);
            }
        });


    }
}