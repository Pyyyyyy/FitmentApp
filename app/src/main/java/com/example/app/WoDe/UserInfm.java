package com.example.app.WoDe;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app.Fragment.FragmenMy;
import com.example.app.R;

public class UserInfm extends AppCompatActivity {

    private TextView phoneNumber;
    private TextView name;
    private TextView idCardNumber;
    private TextView password;
    private TextView zhuxiao;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_infm);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        name = (TextView) findViewById(R.id.name);
        idCardNumber = (TextView) findViewById(R.id.idCardNumber);
        password = (TextView) findViewById(R.id.password);
        zhuxiao = (TextView) findViewById(R.id.zhuxiao);
        back = (ImageView) findViewById(R.id.back);

        SharedPreferences user = getSharedPreferences("user",MODE_PRIVATE);
        phoneNumber.setText(user.getString("phoneNumber",""));
        name.setText(user.getString("name",""));
        idCardNumber.setText(user.getString("idCardNumber",""));
        password.setText(user.getString("password",""));

        zhuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmenMy.user = false;
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
