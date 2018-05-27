package com.example.app.Worker;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.app.FitmentMaterial.FitmentMaterialActivity;
import com.example.app.R;
import com.example.app.wallet;

public class WorkerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        TextView add = (TextView) findViewById(R.id.add);
        NavigationView navView  = (NavigationView) findViewById(R.id.nav_view);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(WorkerActivity.this,WorkerAddActivity.class);
                startActivity(intent);
            }
        });

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_wallet:
                        Intent intent = new Intent(WorkerActivity.this,wallet.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_collection:
                        Toast.makeText(WorkerActivity.this,"你点击了我的收藏",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_follow:
                        Toast.makeText(WorkerActivity.this,"你点击了我的关注 ",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_setting:
                        Toast.makeText(WorkerActivity.this,"你点击了设置",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_appointment:
                        Toast.makeText(WorkerActivity.this,"你点击了我的预约",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_news:
                        Toast.makeText(WorkerActivity.this,"你点击了我的消息",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_order:
                        Toast.makeText(WorkerActivity.this,"你点击了我的订单",Toast.LENGTH_SHORT).show();
                        break;

                }


                return true;
            }


        });
    }
}
