package com.example.app.QingBao.nimu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import android.widget.Button;

import android.widget.ImageView;

import com.example.app.R;



public class nimuActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nimu_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }

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
                Intent intent = new Intent(nimuActivity.this,nimuliucheng.class);

                startActivity(intent);
            }
        });



        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(nimuActivity.this,nimucailiao.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(nimuActivity.this,nimuzhuyishixiang.class);
                startActivity(intent);
            }
        });




    }
}
