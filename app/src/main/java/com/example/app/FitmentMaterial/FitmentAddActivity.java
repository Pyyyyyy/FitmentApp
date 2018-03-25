package com.example.app.FitmentMaterial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.R;

import org.litepal.tablemanager.Connector;

public class FitmentAddActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText name;
    private EditText type;
    private EditText price;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fitment_add_layout);

        name = (EditText) findViewById(R.id.name);
        type = (EditText) findViewById((R.id.type));
        price = (EditText) findViewById(R.id.price);
        description = (EditText) findViewById(R.id.description);
        Button add = (Button) findViewById(R.id.add);
        Button cancle = (Button) findViewById(R.id.cancle);

        add.setOnClickListener(this);
        cancle.setOnClickListener(this);

        Connector.getDatabase();

    }

        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.add:
                    String mName = name.getText().toString().trim();
                    String mType = type.getText().toString().trim();
                    double mPrice=0d;
                    try{
                        mPrice = Double.parseDouble(price.getText().toString().trim());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    String mDescription = description.getText().toString().trim();
                    Material material = new Material();
                    material.setName(mName);
                    material.setType(mType);
                    material.setPrice(mPrice);
                    material.setDescription(mDescription);
                    material.save();
                    Toast.makeText(this,"上架成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FitmentAddActivity.this,FitmentMaterialActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.cancle:
                    Intent intent1 = new Intent(FitmentAddActivity.this,FitmentMaterialActivity.class);
                    startActivity(intent1);
                    break;
            }
        }


    }
