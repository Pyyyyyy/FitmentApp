package com.example.app.WoDe;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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


public class RegisterActivity extends AppCompatActivity {
    private EditText account;
    private EditText name;
    private EditText phone;
    private EditText password;
    private EditText repassword;
    private Button register;
    private Button mCancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        account = (EditText)findViewById(R.id.username);
        name = (EditText)findViewById(R.id.name);
        phone = (EditText)findViewById(R.id.phone);
        password = (EditText)findViewById(R.id.password);
        repassword = (EditText)findViewById(R.id.repassword);


        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                register_check();
                register(account.getText().toString().trim(),password.getText().toString().trim(), name.getText().toString().trim(), Integer.parseInt(phone.getText().toString().trim()));
            }

        });

    }

    public void register_check(){
        if(isUserNameAndPwdValid()){
            String userName = account.getText().toString().trim();
            String personalName = name.getText().toString().trim();
            String pn = phone.getText().toString().trim();
            int phoneNumber = Integer.parseInt(pn);
            String userPwd = password.getText().toString().trim();
            String userRepwd = repassword.getText().toString().trim();

            //两次密码输入不一样
            if(userPwd.equals(userRepwd)==false){
                Toast.makeText(this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                return ;
            }
            /*
            else {
                User mUser = new User(userName,personalName,phoneNumber,userPwd);
                mUserManager.openDataBase();
                long flag = mUserManager.insertUserData(mUser); //新建用户信息
                if (flag == -1) {
                    Toast.makeText(this, "",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
                    Intent intent_Register_to_Login = new Intent(RegisterActivity.this,LoginActivity.class) ;    //切换RegisterActivity至LoginActivity
                    startActivity(intent_Register_to_Login);
                    finish();
                }
            }
            */
        }
    }
    public boolean isUserNameAndPwdValid() {
        if (account.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请输入账号",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请输入密码",
                    Toast.LENGTH_SHORT).show();
            return false;
        }else if(repassword.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请重复密码",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(phone.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请输入手机号",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(name.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请输入姓名",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void register(String account, String password,String name,int phoneNumber) {
        String registerUrlStr = Constant.URL_Register + "?account=" + account + "&password=" + password + "&name=" + name + "&phoneNumber=" + phoneNumber;
        new MyAsyncTask().execute(registerUrlStr);
    }

    public static class MyAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        /**
         * @param params 这里的params是一个数组，即AsyncTask在激活运行是调用execute()方法传入的参数
         */
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            StringBuilder response = new StringBuilder();
            try {
                URL url = new URL(params[0]); // 声明一个URL,注意如果用百度首页实验，请使用https开头，否则获取不到返回报文
                connection = (HttpURLConnection) url.openConnection(); // 打开该URL连接
                connection.setRequestMethod("GET"); // 设置请求方法，“POST或GET”，我们这里用GET，在说到POST的时候再用POST
                connection.setConnectTimeout(80000); // 设置连接建立的超时时间
                connection.setReadTimeout(80000); // 设置网络报文收发超时时间
                InputStream in = connection.getInputStream();  // 通过连接的输入流获取下发报文，然后就是Java的流处理
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.toString(); // 这里返回的结果就作为onPostExecute方法的入参
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // 如果在doInBackground方法，那么就会立刻执行本方法
            // 本方法在UI线程中执行，可以更新UI元素，典型的就是更新进度条进度，一般是在下载时候使用
        }

        /**
         * 运行在UI线程中，所以可以直接操作UI元素
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {

        }

    }
}
