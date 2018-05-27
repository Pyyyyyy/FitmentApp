package com.example.app.QingBao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.app.R;

import static com.example.app.R.id.button2;

/**
 * Created by 1 on 2018/1/6.
 */

public class shuidianActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shuidian_layout);

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
                Intent intent = new Intent(shuidianActivity.this,shuidianliuchengActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(shuidianActivity.this,shuidiancailiao.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(shuidianActivity.this,shuidianzhuyishixian.class);
                startActivity(intent);
            }
        });

    }
}
