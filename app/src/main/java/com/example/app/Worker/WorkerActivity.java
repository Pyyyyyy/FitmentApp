package com.example.app.Worker;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;


import com.example.app.R;
import com.example.app.http.BaseActivity;
import com.example.app.http.CommonRequest;
import com.example.app.http.CommonResponse;
import com.example.app.http.ResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkerActivity extends BaseActivity {
    private String URL_FITMENT = "http://w2062389t3.iask.in:39931/FitmentApp/FitmentMaterialServlet";

    private SwipeRefreshLayout swipeRefresh;

    private List<Worker> workerList = new ArrayList<>();

    private WorkerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        TextView add = (TextView) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(WorkerActivity.this,WorkerAddActivity.class);
                startActivity(intent);
            }
        });


        fitmentWorker();


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new WorkerAdapter(workerList);
        recyclerView.setAdapter(adapter);


        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshWorker();
            }
        });
    }

    public void fitmentWorker(){
        final CommonRequest request = new CommonRequest();
        sendHttpPostRequest(URL_FITMENT,request,new ResponseHandler(){
            @Override
            public void success(CommonResponse response) {
                workerList.clear();
                ArrayList<HashMap<String, String>> workers = response.getDataList();
                for(HashMap<String,String> worker:workers){
                    byte[] bytes = Base64.decode(worker.get("picture"), Base64.DEFAULT);
                    Worker worker1 = new Worker(worker.get("name").toString(), BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                    workerList.add(worker1);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(WorkerActivity.this,failMsg,Toast.LENGTH_SHORT).show();
            }

        });

    }




    private void refreshWorker(){
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

                        fitmentWorker();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
