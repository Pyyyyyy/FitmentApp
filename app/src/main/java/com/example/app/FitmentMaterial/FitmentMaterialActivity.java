package com.example.app.FitmentMaterial;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app.R;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class FitmentMaterialActivity extends AppCompatActivity {
    private List<Material>materialList = new ArrayList<>();
    private MaterialAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fitment_material_layout);
        Button button01 = (Button) findViewById(R.id.button_shoppingcar);
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
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FitmentMaterialActivity.this,ShoppingCar.class);
                startActivity(intent);
            }
        });
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
}
