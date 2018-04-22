package com.example.app.WoDe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.constant.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class LoginActivity extends AppCompatActivity {
    private EditText account;                        //用户名编辑
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

        account = (EditText) findViewById(R.id.account);
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
                    login(account.getText().toString().trim(),password.getText().toString().trim());
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
        if (account.getText().toString().trim().equals("")) {
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

    public void login(String account,String password) {                                              //登录按钮监听事件


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
            String loginUrlStr = Constant.URL_Login + "?account=" + account + "&password=" + password;
            new MyAsyncTask().execute(loginUrlStr);

            //}else if(result==0){
            //Toast.makeText(this, "登录失败",Toast.LENGTH_SHORT).show();  //登录失败提示
            //}
    }

    public class MyAsyncTask extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute(){
        }

        @Override
        protected String doInBackground(String...params){
            HttpURLConnection connection = null;
            StringBuilder response = new StringBuilder();
            try{
                URL url = new URL(params[0]);
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(8000);
                connection.setReadTimeout(8000);
                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while((line = reader.readLine()) != null){
                    response.append(line);
                }

            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
            return response.toString();
        }

        @Override
        protected void onProgressUpdate(Integer...value){
        }

        @Override
        protected void onPostExecute(String message){
            Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
            if(message.equals("登录成功")){
                finish();
            }
        }
    }


}