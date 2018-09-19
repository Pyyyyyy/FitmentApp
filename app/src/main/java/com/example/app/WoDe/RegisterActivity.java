package com.example.app.WoDe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.app.R;
import com.example.app.http.BaseActivity;
import com.example.app.http.CommonRequest;
import com.example.app.http.CommonResponse;
import com.example.app.http.ResponseHandler;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.TextView;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;



public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    //private String URL_REGISTER = "http://w2062389t3.iask.in:42216/FitmentApp/RegisterServlet";
  /*

    private EditText name;
    private EditText idCardNumber;
    private EditText password;
    private ImageView iv_showPassword;
    private EditText repassword;
    private ImageView iv_showRepassword;
    private Boolean showPassword = true;
    private Boolean showRepassword = true;
 */
   private EditText phoneNumber;
    private EditText code;
    private Button sendMsg;


    private TextView next;
    private ImageView back;

    private int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
/*

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
        */

        phoneNumber = (EditText)findViewById(R.id.phoneNumber);
        code = (EditText)findViewById(R.id.code);
        sendMsg = (Button)findViewById((R.id.sendMsg));

        next = (TextView)findViewById((R.id.next));
        back = (ImageView)findViewById(R.id.back);

        SMSSDK.registerEventHandler(eventHandler);
        sendMsg.setOnClickListener(this);
        next.setOnClickListener(this);
        next.setClickable(false);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String phoneNum = phoneNumber.getText().toString().trim();
        switch (v.getId()) {
            case R.id.sendMsg:
                if(phoneNumber.getText().toString().trim().length()!=11)
                {
                    Toast.makeText(RegisterActivity.this,"手机号码位数不正确",Toast.LENGTH_SHORT).show();
                    return ;
                }
                String num="[1][358]\\d{9}";
                if(phoneNum.matches(num)) {
                    SMSSDK.getVerificationCode("86", phoneNum);
                    sendMsg.setClickable(false);
                    next.setClickable(true);
                    //开始倒计时
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (i = 60; i > 0; i--) {
                                handler.sendEmptyMessage(-1);
                                if (i <= 0) {
                                    break;
                                }
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            //倒计时结束执行
                            handler.sendEmptyMessage(-2);
                        }
                    }).start();
                }else{
                    Toast.makeText(RegisterActivity.this,"手机号码不正确",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.next:
                String mCode = code.getText().toString().trim();
                if (TextUtils.isEmpty(phoneNum)) {
                    Toast.makeText(getApplicationContext(), "手机号码不能为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mCode)) {
                    Toast.makeText(getApplicationContext(), "验证码不能为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                SMSSDK.submitVerificationCode("86", phoneNum, mCode);
                break;
            case R.id.back:
                finish();
                break;
                /*
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
                */
            default:
                break;
        }
    }


    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -1) {
                //修改控件文本进行倒计时
                sendMsg.setText( i+" s");
            } else if (msg.what == -2) {
                //修改控件文本，进行重新发送验证码
                sendMsg.setText("重新发送");
                sendMsg.setClickable(true);
                i = 60;
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;

                // 短信注册成功后
                if (result == SMSSDK.RESULT_COMPLETE) {
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        Intent intent = new Intent(RegisterActivity.this,Register2Activity.class);
                        intent.putExtra("phoneNumber",phoneNumber.getText().toString().trim());
                        startActivity(intent);
                        /*
                        if (register_check()) {
                            register(phoneNumber.getText().toString().trim(), name.getText().toString().trim(), idCardNumber.getText().toString().trim(), password.getText().toString().trim());
                            }
                            */

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(getApplicationContext(), "验证码已经发送",
                                Toast.LENGTH_SHORT).show();
                    }
                }else if (result == SMSSDK.RESULT_ERROR) {
                    Toast.makeText(RegisterActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
                    } else {
                        ((Throwable) data).printStackTrace();
                    }

            }

        }
    };

    // .registerEventHandler是用来往SMSSDK中注册一个事件接收器。
    EventHandler eventHandler = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            handler.sendMessage(msg);
        }
    };
/*
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
                Toast.makeText(RegisterActivity.this, response.getResMsg(), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(RegisterActivity.this, failMsg, Toast.LENGTH_SHORT).show();

            }

        });
    }
    */

    //在完成短信验证之后，需要销毁回调监听接口。
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}



