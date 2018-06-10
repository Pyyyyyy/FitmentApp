package com.example.app.QingBao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
<<<<<<< HEAD
=======
import android.widget.Button;
>>>>>>> 9670e15bde4593bdcfa459debcb07a8ec6ef519f
import android.widget.ImageView;

import com.example.app.QingBao.jiaju.jiajucailiao;
import com.example.app.QingBao.jiaju.jiajuliucheng;
import com.example.app.QingBao.jiaju.jiajuzhuyishixiang;
import com.example.app.QingBao.tumu.tumucailiao;
import com.example.app.QingBao.tumu.tumuliucheng;
import com.example.app.QingBao.tumu.tumuzhuyishixiang;
import com.example.app.R;

/**
 * Created by 1 on 2018/1/6.
 */

public class jiajuActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiaju_layout);
<<<<<<< HEAD
        ImageView imageView01 = (ImageView) findViewById(R.id.lostandfound_back_imageview);
        imageView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jiajuActivity.this,zhuangxiuzhongActivity.class);
                startActivity(intent);
            }
        });
=======

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
                Intent intent = new Intent(jiajuActivity.this,jiajuliucheng.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(jiajuActivity.this,jiajucailiao.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(jiajuActivity.this,jiajuzhuyishixiang.class);
                startActivity(intent);
            }
        });

>>>>>>> 9670e15bde4593bdcfa459debcb07a8ec6ef519f
    }
}