package com.example.app.QingBao;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.app.QingBao.chuyu.chuyuActivity;
import com.example.app.QingBao.dengju.dengjuActivity;
import com.example.app.QingBao.jiaju.jiajuActivity;
import com.example.app.QingBao.nimu.nimuActivity;
import com.example.app.QingBao.shuidian.shuidianActivity;
import com.example.app.QingBao.tumu.tumuActivity;
import com.example.app.QingBao.youqi.youqiActivity;
import com.example.app.R;

public class zhuangxiuzhongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuangxiuzhong_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }

        ImageButton button1 = (ImageButton) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(zhuangxiuzhongActivity.this,shuidianActivity.class);
                startActivity(intent);
            }
        });

        ImageButton button2 = (ImageButton) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(zhuangxiuzhongActivity.this,nimuActivity.class);
                startActivity(intent);
            }
        });

        ImageButton button3 = (ImageButton) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(zhuangxiuzhongActivity.this,tumuActivity.class);
                startActivity(intent);
            }
        });

        ImageButton button4 = (ImageButton) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(zhuangxiuzhongActivity.this,youqiActivity.class);
                startActivity(intent);
            }
        });

        ImageButton button5 = (ImageButton) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(zhuangxiuzhongActivity.this,chuyuActivity.class);
                startActivity(intent);
            }
        });

        ImageButton button6 = (ImageButton) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(zhuangxiuzhongActivity.this,dengjuActivity.class);
                startActivity(intent);
            }
        });

        ImageButton button7 = (ImageButton) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(zhuangxiuzhongActivity.this,jiajuActivity.class);
                startActivity(intent);
            }
        });
        ImageView imageView01 = (ImageView) findViewById(R.id.lostandfound_back_imageview);
        imageView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
