package com.example.app.QingBao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.widget.ImageButton;

import com.example.app.R;

public class zhuangxiuzhongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuangxiuzhong_layout);

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


    }
}
