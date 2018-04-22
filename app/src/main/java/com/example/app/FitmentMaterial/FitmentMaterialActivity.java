package com.example.app.FitmentMaterial;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app.R;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class FitmentMaterialActivity extends AppCompatActivity {
    private List<Material>materialsName = new ArrayList<>();

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
        initMaterials();
        MaterialAdapter adapter = new MaterialAdapter(this,R.layout.material_item,materialsName);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    private void initMaterials(){
        List<Material>materials = DataSupport.findAll(Material.class);
        for(Material material:materials){
            Material name = new Material(material.getName());
            materialsName.add(name);
        }

    }
}
