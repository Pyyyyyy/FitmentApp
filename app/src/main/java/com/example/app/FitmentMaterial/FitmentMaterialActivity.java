package com.example.app.FitmentMaterial;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.Worker.WorkerActivity;
import com.example.app.http.BaseActivity;
import com.example.app.http.CommonRequest;
import com.example.app.http.CommonResponse;
import com.example.app.http.ResponseHandler;
import com.example.app.main.MainActivity;
import com.example.app.wallet;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FitmentMaterialActivity extends BaseActivity {
    private String URL_FITMENT = "http://w2062389t3.iask.in:39931/FitmentApp/FitmentMaterialServlet";
    private SwipeRefreshLayout swipeRefresh;

    private List<Material>materialList = new ArrayList<>();
    private MaterialAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fitment_material_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }

        Button button01 = (Button) findViewById(R.id.button_shoppingcar);
        TextView add = (TextView) findViewById(R.id.add);
        ImageView cancel = (ImageView) findViewById(R.id.cancel);
        NavigationView navView  = (NavigationView) findViewById(R.id.nav_view);

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(FitmentMaterialActivity.this,FitmentAddActivity.class);
                startActivity(intent);
            }
        });

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FitmentMaterialActivity.this,ShoppingCar.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_wallet:
                        Intent intent = new Intent(FitmentMaterialActivity.this,wallet.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_collection:
                        Toast.makeText(FitmentMaterialActivity.this,"你点击了我的收藏",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_follow:
                        Toast.makeText(FitmentMaterialActivity.this,"你点击了我的关注 ",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_setting:
                        Toast.makeText(FitmentMaterialActivity.this,"你点击了设置",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_appointment:
                        Toast.makeText(FitmentMaterialActivity.this,"你点击了我的预约",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_news:
                        Toast.makeText(FitmentMaterialActivity.this,"你点击了我的消息",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_order:
                        Toast.makeText(FitmentMaterialActivity.this,"你点击了我的订单",Toast.LENGTH_SHORT).show();
                        break;

                }


                return true;
            }


        });

        fitmentMaterial();


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MaterialAdapter(materialList);
        recyclerView.setAdapter(adapter);


        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMaterial();
            }
        });




    }


    public void fitmentMaterial(){
        final CommonRequest request = new CommonRequest();
        sendHttpPostRequest(URL_FITMENT,request,new ResponseHandler(){
            @Override
            public void success(CommonResponse response) {

                ArrayList<HashMap<String, String>> materials = response.getDataList();
                for(HashMap<String,String> material:materials){
                    byte[] bytes = Base64.decode(material.get("picture"), Base64.DEFAULT);
                    Material material1 = new Material(material.get("name").toString(), BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                    materialList.add(material1);
                }
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(FitmentMaterialActivity.this,failMsg,Toast.LENGTH_SHORT).show();
            }

        });

    }



    private void refreshMaterial(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
