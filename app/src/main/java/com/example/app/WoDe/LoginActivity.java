package com.example.app.WoDe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.MainActivity;
import com.example.app.R;


public class LoginActivity extends AppCompatActivity {
    private EditText username;                        //用户名编辑
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
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Login_Remember = (CheckBox) findViewById(R.id.Login_Remember);
        login_sp = getSharedPreferences("userInfo", 0);
        String name=login_sp.getString("USER_NAME", "");
        String pwd =login_sp.getString("PASSWORD", "");
        boolean choseRemember =login_sp.getBoolean("Login_Remember", false);
        //如果上次选了记住密码，那进入登录页面也自动勾选记住密码，并填上用户名和密码
        if(choseRemember){
            username.setText(name);
            password.setText(pwd);
            Login_Remember.setChecked(true);
        }

        if (mUserDataManager == null) {
            mUserDataManager = new UserManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }
        login = (Button)findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                login();
            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public void login() {                                              //登录按钮监听事件
        if (isUserNameAndPwdValid()) {
            String userName = username.getText().toString().trim();    //获取当前输入的用户名和密码信息
            String userPwd = password.getText().toString().trim();
            SharedPreferences.Editor editor = login_sp.edit();
            int result = mUserDataManager.findUserByNameAndPwd(userName, userPwd);
            if(result==1){                                             //返回1说明用户名和密码均正确
                //保存用户名和密码
                editor.putString("USER_NAME", userName);
                editor.putString("PASSWORD", userPwd);

                //是否记住密码
                if(Login_Remember.isChecked()){
                   editor.putBoolean("Login_Remember", true);
                }else{
                   editor.putBoolean("Login_Remember", false);
                }
                editor.commit();

                Intent intent = new Intent(LoginActivity.this,MainActivity.class) ;    //切换LoginActivity至MainActivity
                startActivity(intent);
                finish();
                Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();//登录成功提示
            }else if(result==0){
                Toast.makeText(this, "登录失败",Toast.LENGTH_SHORT).show();  //登录失败提示
            }
        }
    }

    public boolean isUserNameAndPwdValid() {
        if (username.getText().toString().trim().equals("")) {
            Toast.makeText(this,"用户名不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.getText().toString().trim().equals("")) {
            Toast.makeText(this,"密码不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
