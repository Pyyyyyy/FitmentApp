package com.example.app.WoDe;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.http.BaseActivity;
import com.example.app.http.CommonRequest;
import com.example.app.http.CommonResponse;
import com.example.app.http.ResponseHandler;



public class RegisterActivity extends BaseActivity {
    private String URL_REGISTER = "http://w2062389t3.iask.in:39931/FitmentApp/RegisterServlet";

    private EditText account;
    private EditText name;
    private EditText phoneNumber;
    private EditText password;
    private EditText repassword;
    private Button register;
    private Button cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        Log.d("RegisterActivity","reaper");
        account = (EditText)findViewById(R.id.account);
        name = (EditText)findViewById(R.id.name);
        phoneNumber = (EditText)findViewById(R.id.phoneNumber);
        password = (EditText)findViewById(R.id.password);
        repassword = (EditText)findViewById(R.id.repassword);
        //Log.d("RegisterActivity","reaper"+name.getText().toString());

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                if(register_check()) {//判断用户输入是否正常
                    register(account.getText().toString().trim(), password.getText().toString().trim(), name.getText().toString().trim(), phoneNumber.getText().toString().trim());
                }

            }

        });
        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });


    }

    public boolean register_check(){
        if (account.getText().toString().trim().equals("")) {
            Toast.makeText(this, "账号不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (name.getText().toString().trim().equals("")) {
            Toast.makeText(this, "姓名不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }else if(phoneNumber.getText().toString().trim().equals("")) {
            Toast.makeText(this, "手机号不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(password.getText().toString().trim().equals("")) {
            Toast.makeText(this, "密码不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(repassword.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请重复密码",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
            String userPwd = password.getText().toString().trim();
            String userRepwd = repassword.getText().toString().trim();

            //两次密码输入不一样
            if(!userPwd.equals(userRepwd)){
                Toast.makeText(this,"密码不一致",Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;


        }


     private void register(String account, String password,String name,String phoneNumber) {
         final CommonRequest request = new CommonRequest();
         request.addRequestParam("account",account);
         request.addRequestParam("password",password);
         request.addRequestParam("name",name);
         request.addRequestParam("phoneNumber",phoneNumber);
         sendHttpPostRequest(URL_REGISTER,request,new ResponseHandler(){
             @Override
             public void success(CommonResponse response) {
                 Toast.makeText(RegisterActivity.this,response.getResMsg(),Toast.LENGTH_SHORT).show();
                 finish();
             }

             @Override
             public void fail(String failCode, String failMsg) {
                 Toast.makeText(RegisterActivity.this,failMsg,Toast.LENGTH_SHORT).show();

             }

         });


    }


}
