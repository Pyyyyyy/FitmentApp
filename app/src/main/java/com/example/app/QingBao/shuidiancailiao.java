package com.example.app.QingBao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.app.R;

public class shuidiancailiao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuidiancailiao);
        ImageView imageView01 = (ImageView) findViewById(R.id.more_back_imageview);
        imageView01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shuidiancailiao.this,shuidianActivity.class);
                startActivity(intent);
            }
        });
    }
}
