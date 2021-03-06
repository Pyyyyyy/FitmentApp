package com.example.app.WoDe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app.Fragment.FragmenMy;
import com.example.app.R;
import com.example.app.http.BaseActivity;
import com.example.app.http.CommonRequest;
import com.example.app.http.CommonResponse;
import com.example.app.http.ResponseHandler;

import java.util.HashMap;


public class LoginActivity extends BaseActivity {
    private String URL_LOGIN = "http://w2062389t3.iask.in:23762/FitmentApp/LoginServlet";

    private EditText phoneNumber;                        //用户名编辑
    private EditText password;                            //密码编辑
    private Button login;                   //登录按钮
    private Button register;                      //注册按钮
    private CheckBox Login_Remember;         //记住密码

    private SharedPreferences login_sp;
    private UserManager mUserDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }

        ImageView back =  (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        password = (EditText) findViewById(R.id.password);
        Login_Remember = (CheckBox) findViewById(R.id.Login_Remember);

        /*
        login_sp = getSharedPreferences("userInfo", 0);
        String name = login_sp.getString("USER_NAME", "");
        String pwd = login_sp.getString("PASSWORD", "");
        boolean choseRemember = login_sp.getBoolean("Login_Remember", false);
        //如果上次选了记住密码，那进入登录页面也自动勾选记住密码，并填上用户名和密码
        if (choseRemember) {
            account.setText(name);
            password.setText(pwd);
            Login_Remember.setChecked(true);
        }

        if (mUserDataManager == null) {
            mUserDataManager = new UserManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }
        */
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login_check()) {
                    login(phoneNumber.getText().toString().trim(),password.getText().toString().trim());
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean login_check() {
        if (phoneNumber.getText().toString().trim().equals("")) {
            Toast.makeText(this, "账号不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.getText().toString().trim().equals("")) {
            Toast.makeText(this, "密码不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void login(String phoneNumber,String password) {                                              //登录按钮监听事件


        //SharedPreferences.Editor editor = login_sp.edit();
        //保存用户名和密码
        // editor.putString("USER_NAME", userName);
        //editor.putString("PASSWORD", userPwd);

        //是否记住密码
        //if (Login_Remember.isChecked()) {
        // editor.putBoolean("Login_Remember", true);
        // } else {
        //   editor.putBoolean("Login_Remember", false);
        // }
        //editor.commit();

        final CommonRequest request = new CommonRequest();
        request.addRequestParam("phoneNumber",phoneNumber);
        request.addRequestParam("password", password);
        sendHttpPostRequest(URL_LOGIN, request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                Toast.makeText(LoginActivity.this,response.getResMsg(),Toast.LENGTH_SHORT).show();
                HashMap<String, String> user = response.getPropertyMap();
                SharedPreferences.Editor editor = getSharedPreferences("user",MODE_PRIVATE).edit();
                editor.putString("phoneNumber",user.get("phoneNumber"));
                editor.putString("name",user.get("name"));
                editor.putString("idCardNumber",user.get("idCardNumber"));
                editor.putString("password",user.get("password"));
                editor.apply();
                FragmenMy.user = true;
                finish();
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(LoginActivity.this,failMsg,Toast.LENGTH_SHORT).show();

            }

        });
    }


}