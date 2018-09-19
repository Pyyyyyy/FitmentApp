package com.example.app.WoDe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.http.BaseActivity;
import com.example.app.http.CommonRequest;
import com.example.app.http.CommonResponse;
import com.example.app.http.ResponseHandler;

public class Register2Activity extends BaseActivity implements View.OnClickListener{
    private String URL_REGISTER = "http://w2062389t3.iask.in:42216/FitmentApp/RegisterServlet";

    private EditText name;
    private EditText idCardNumber;
    private EditText password;
    private ImageView iv_showPassword;
    private EditText repassword;
    private ImageView iv_showRepassword;
    private Boolean showPassword = true;
    private Boolean showRepassword = true;
    private TextView register;
    private ImageView back;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }

        name = (EditText)findViewById(R.id.name);
        idCardNumber = (EditText)findViewById(R.id.idCardNumber);
        password = (EditText)findViewById(R.id.password);
        iv_showPassword = (ImageView) findViewById(R.id.iv_showPassword);
        iv_showPassword.setImageDrawable(getResources().getDrawable(R.drawable.passwordn));
        iv_showRepassword = (ImageView) findViewById(R.id.iv_showRepassword);
        iv_showRepassword.setImageDrawable(getResources().getDrawable(R.drawable.passwordn));
        iv_showPassword.setOnClickListener(this);
        iv_showRepassword.setOnClickListener(this);
        repassword = (EditText)findViewById(R.id.repassword);
        register = (TextView)findViewById((R.id.register));
        back = (ImageView)findViewById(R.id.back);

        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("phoneNumber");

        register.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.iv_showPassword:
                if (showPassword) {// 显示密码
                    iv_showPassword.setImageDrawable(getResources().getDrawable(R.drawable.passwordy));
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    password.setSelection(password.getText().toString().length());
                    showPassword = !showPassword;
                } else {// 隐藏密码
                    iv_showPassword.setImageDrawable(getResources().getDrawable(R.drawable.passwordn));
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password.setSelection(password.getText().toString().length());
                    showPassword = !showPassword;
                }
                break;
            case R.id.iv_showRepassword:
                if (showRepassword) {// 显示密码
                    iv_showRepassword.setImageDrawable(getResources().getDrawable(R.drawable.passwordy));
                    repassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    repassword.setSelection(repassword.getText().toString().length());
                    showRepassword = !showRepassword;
                } else {// 隐藏密码
                    iv_showRepassword.setImageDrawable(getResources().getDrawable(R.drawable.passwordn));
                    repassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    repassword.setSelection(repassword.getText().toString().length());
                    showRepassword = !showRepassword;
                }
                break;
            case R.id.register:
                if(register_check()){
                    register(phoneNumber, name.getText().toString().trim(), idCardNumber.getText().toString().trim(), password.getText().toString().trim());
                }
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }

    public boolean register_check(){
        if (name.getText().toString().trim().equals("")) {
            Toast.makeText(this, "姓名不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }else if(idCardNumber.getText().toString().trim().equals("")) {
            Toast.makeText(this, "身份证号码不能为空",
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

    private void register(String phoneNumber, String name,String idCardNumber,String password) {
        final CommonRequest request = new CommonRequest();
        request.addRequestParam("phoneNumber", phoneNumber);
        request.addRequestParam("name", name);
        request.addRequestParam("idCardNumber", idCardNumber);
        request.addRequestParam("password", password);
        sendHttpPostRequest(URL_REGISTER, request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                Toast.makeText(Register2Activity.this, response.getResMsg(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register2Activity.this,LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(Register2Activity.this, failMsg, Toast.LENGTH_SHORT).show();

            }

        });
    }
}
