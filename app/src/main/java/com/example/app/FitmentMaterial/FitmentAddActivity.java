package com.example.app.FitmentMaterial;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.R;
import com.example.app.http.BaseActivity;
import com.example.app.http.CommonRequest;
import com.example.app.http.CommonResponse;
import com.example.app.http.ResponseHandler;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FitmentAddActivity extends BaseActivity implements View.OnClickListener{
    private String URL_FITMENT_ADD = "http://w2062389t3.iask.in:39931/FitmentApp/FitmentAddServlet";

    public static final int TAKE_PHOTO =1;
    public static final int CHOOSE_PHOTO = 2;
    private static int RESULT_LOAD_IMAGE=1;
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
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    startActivityForResult(intent,TAKE_PHOTO);
                    break;

                case R.id.add:
                    if(add_check()){
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                            bitmap.compress(Bitmap.CompressFormat.PNG, 10, baos);
                            fitmentAdd(name.getText().toString().trim(),type.getText().toString().trim(),price.getText().toString().trim(),description.getText().toString().trim(),baos.toByteArray());
                        }catch(FileNotFoundException e){
                            e.printStackTrace();
                        }

                    }


                    Toast.makeText(FitmentAddActivity.this,"上架成功",Toast.LENGTH_SHORT).show();
                    finish();

                    break;

                case R.id.choose_from_album:
                    if (ContextCompat.checkSelfPermission(FitmentAddActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(FitmentAddActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    } else{
                        openAlbum();
                    }
                    break;

                case R.id.cancel:
                    finish();
                    break;
            }
    }
    public void openAlbum(){
        Intent intent02 = new Intent("android.intent.action.GET_CONTENT");
        intent02.setType("image/*");
        startActivityForResult(intent02, CHOOSE_PHOTO);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int [] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else {
                    Toast.makeText(this,"You denied the permission",
                            Toast.LENGTH_SHORT).show();

                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
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

      public void fitmentAdd(String name,String type,String price,String description,byte[] picture){


          final ProgressDialog  progressDialog = new ProgressDialog(this);
          progressDialog.setTitle("Wait");
          progressDialog.setMessage("Loading.");
          progressDialog.setCancelable(false);
          progressDialog.show();

          final CommonRequest request = new CommonRequest();
          request.addRequestParam("name",name);
          request.addRequestParam("type",type);
          request.addRequestParam("price",price);
          request.addRequestParam("description",description);
          request.addRequestParam("picture", Base64.encodeToString(picture, 0));

          sendHttpPostRequest(URL_FITMENT_ADD,request,new ResponseHandler(){
              @Override
              public void success(CommonResponse response) {
                  progressDialog.dismiss();
                  Toast.makeText(FitmentAddActivity.this,response.getResMsg(),Toast.LENGTH_SHORT).show();
                  finish();
              }

              @Override
              public void fail(String failCode, String failMsg) {
                  progressDialog.dismiss();
                  Toast.makeText(FitmentAddActivity.this,failMsg,Toast.LENGTH_SHORT).show();

              }

          });

     }


    }




