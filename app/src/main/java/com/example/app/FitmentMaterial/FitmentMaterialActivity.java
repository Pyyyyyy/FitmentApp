package com.example.app.FitmentMaterial;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.http.BaseActivity;
import com.example.app.http.CommonRequest;
import com.example.app.http.CommonResponse;
import com.example.app.http.ResponseHandler;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class FitmentMaterialActivity extends BaseActivity {
    private String URL_FITMENT = "http://w2062389t3.iask.in:39931/FitmentApp/FitmentMaterialServlet";

    private List<Material>materialList = new ArrayList<>();

    private MaterialAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fitment_material_layout);
        TextView add = (TextView) findViewById(R.id.add);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(FitmentMaterialActivity.this,FitmentAddActivity.class);
                startActivity(intent);
            }
        });
        fitmentMaterial();


        initMaterials();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MaterialAdapter(materialList);
        recyclerView.setAdapter(adapter);




    }

    private void initMaterials(){
        List<Material>materials = DataSupport.findAll(Material.class);
        for(Material material:materials){
            Material name = new Material(material.getName());
            materialList.add(name);
        }

    }

    public void fitmentMaterial(){
        final CommonRequest request = new CommonRequest();
        sendHttpPostRequest(URL_FITMENT,request,new ResponseHandler(){
            @Override
            public void success(CommonResponse response) {

                finish();
            }

            @Override
            public void fail(String failCode, String failMsg) {

            }

        });

    }
}
