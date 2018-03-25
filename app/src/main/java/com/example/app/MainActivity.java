package com.example.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.example.app.FitmentMaterial.FitmentMaterialActivity;
import com.example.app.QingBao.zhuangxiuzhongActivity;
import com.example.app.WoDe.UserActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button image4 = (Button) findViewById(R.id.image4);
        image4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,zhuangxiuqianActivity.class);
                startActivity(intent);
            }
        });

        Button image5 = (Button) findViewById(R.id.image5);
        image5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,zhuangxiuzhongActivity.class);
                startActivity(intent);
            }
        });

        Button user = (Button) findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });

        Button fitmentmaterial = (Button) findViewById(R.id.fitmentmaterial);
        fitmentmaterial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, FitmentMaterialActivity.class);
                startActivity(intent);
            }
        });

    }
}
