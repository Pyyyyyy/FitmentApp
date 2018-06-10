package com.example.app.QingBao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.app.R;

/**
 * Created by 1 on 2018/1/6.
 */

public class tumuActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tumu_layout);
        ImageView imageView01 = (ImageView) findViewById(R.id.lostandfound_back_imageview);
        imageView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tumuActivity.this,zhuangxiuzhongActivity.class);
                startActivity(intent);
            }
        });
    }
}
