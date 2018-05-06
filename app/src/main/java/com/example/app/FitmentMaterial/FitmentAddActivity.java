package com.example.app.FitmentMaterial;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;

import org.litepal.tablemanager.Connector;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FitmentAddActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int TAKE_PHOTO =1;
    private ImageView picture;
    private Uri imageUri;

    private EditText name;
    private EditText type;
    private EditText price;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fitment_add_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }

        name = (EditText) findViewById(R.id.name);
        type = (EditText) findViewById((R.id.type));
        price = (EditText) findViewById(R.id.price);
        description = (EditText) findViewById(R.id.description);
        TextView add = (TextView) findViewById(R.id.add);
        ImageView cancel = (ImageView) findViewById(R.id.cancel);
        Button takePhoto = (Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.picture);
        Button chooseFromAlbum = (Button) findViewById(R.id.choose_from_album);

        add.setOnClickListener(this);
        cancel.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
        chooseFromAlbum.setOnClickListener(this);

        Connector.getDatabase();

    }

        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.take_photo:
                    File outputImage = new File(getExternalCacheDir(),"output_image.jpg");
                    try{
                        if(outputImage.exists()){
                            outputImage.delete();
                        }
                        outputImage.createNewFile();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    if(Build.VERSION.SDK_INT>=24){
                        imageUri = FileProvider.getUriForFile(FitmentAddActivity.this,"com.example.cameraalbumtest.fileprovider",outputImage);
                    }else{
                        imageUri = Uri.fromFile(outputImage);
                    }
                    Intent intent1 = new Intent("android.media.action.IMAGE_CAPTURE");
                    intent1.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    startActivityForResult(intent1,TAKE_PHOTO);
                    break;
                case R.id.add:
                    if(add_check()){
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        }catch(FileNotFoundException e){
                            e.printStackTrace();
                        }

                    }
                    /*
                    String mName = name.getText().toString().trim();
                    String mType = type.getText().toString().trim();
                    float mPrice=0f;
                    try{
                        mPrice = Float.parseFloat(price.getText().toString().trim());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    String mDescription = description.getText().toString().trim();
                    //int mImageId =imageId.getId();

                    Material material = new Material();
                    material.setName(mName);
                    material.setType(mType);
                    material.setPrice(mPrice);
                    material.setDescription(mDescription);
                    //material.setImageId(mImageId);
                    material.save();
                    */
                    Toast.makeText(FitmentAddActivity.this,"上架成功",Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case R.id.cancel:
                    finish();
                    break;
            }
        }

        @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch(requestCode){
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK){
                    try{
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    }catch(FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
    public boolean add_check() {
        if (name.getText().toString().trim().equals("")) {
            Toast.makeText(this, "商品名称不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (type.getText().toString().trim().equals("")) {
            Toast.makeText(this, "类型不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (price.getText().toString().trim().equals("")) {
            Toast.makeText(this, "价格不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (description.getText().toString().trim().equals("")) {
            Toast.makeText(this, "描述不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void fitmentAdd(String name,String type,float price,String description,byte[] picture){

    }


    }




