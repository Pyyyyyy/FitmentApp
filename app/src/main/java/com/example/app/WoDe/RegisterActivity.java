package com.example.app.WoDe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.R;


public class RegisterActivity extends AppCompatActivity {
    private EditText username;
    private EditText name;
    private EditText phone;
    private EditText password;
    private EditText repassword;
    private Button register;
    private Button mCancelButton;
    private UserManager mUserManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        username = (EditText)findViewById(R.id.username);
        name = (EditText)findViewById(R.id.name);
        phone = (EditText)findViewById(R.id.phone);
        password = (EditText)findViewById(R.id.password);
        repassword = (EditText)findViewById(R.id.repassword);
        if (mUserManager == null) {
            mUserManager = new UserManager(this);
            mUserManager.openDataBase();                              //建立本地数据库
        }

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                register_check();
            }

        });

    }

    public void register_check(){
        if(isUserNameAndPwdValid()){
            String userName = username.getText().toString().trim();
            String personalName = name.getText().toString().trim();
            String pn = phone.getText().toString().trim();
            int phoneNumber = Integer.parseInt(pn);
            String userPwd = password.getText().toString().trim();
            String userRepwd = repassword.getText().toString().trim();
            //检查用户是否存在
            int count = mUserManager.findUserByName(userName);
            //用户已经存在时返回，给出提示文字
            if(count>0){
                Toast.makeText(this,"用户名已存在",Toast.LENGTH_SHORT).show();
                return ;
            }
            //两次密码输入不一样
            if(userPwd.equals(userRepwd)==false){
                Toast.makeText(this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                return ;
            }
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
        }
    }
    public boolean isUserNameAndPwdValid() {
        if (username.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请输入用户名",
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
        return true;
    }
}
