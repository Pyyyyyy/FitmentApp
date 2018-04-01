package com.example.app.FitmentMaterial;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app.R;

import org.litepal.tablemanager.Connector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FitmentAddActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int TAKE_PHOTO =1;
    private ImageView picture;
    private Uri imageUri;

    private EditText name;
    private EditText type;
    private EditText price;
    private EditText description;
    //private ImageView imageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fitment_add_layout);

        name = (EditText) findViewById(R.id.name);
        type = (EditText) findViewById((R.id.type));
        price = (EditText) findViewById(R.id.price);
        description = (EditText) findViewById(R.id.description);
        //imageId = (ImageView) findViewById(R.id.picture);
        Button add = (Button) findViewById(R.id.add);
        Button cancle = (Button) findViewById(R.id.cancle);
        Button takePhoto = (Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.picture);
        Button chooseFromAlbum = (Button) findViewById(R.id.choose_from_album);

        add.setOnClickListener(this);
        cancle.setOnClickListener(this);
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
                    String mName = name.getText().toString().trim();
                    String mType = type.getText().toString().trim();
                    double mPrice=0d;
                    try{
                        mPrice = Double.parseDouble(price.getText().toString().trim());
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
                    Toast.makeText(FitmentAddActivity.this,"上架成功",Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(FitmentAddActivity.this,FitmentMaterialActivity.class);
                    startActivity(intent2);
                    finish();
                    break;
                case R.id.cancle:
                    Intent intent3 = new Intent(FitmentAddActivity.this,FitmentMaterialActivity.class);
                    startActivity(intent3);
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


    }
