package com.example.app.http;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


/**
 * Created by 1 on 2018/5/5.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void sendHttpPostRequest(String url, CommonRequest request, ResponseHandler responseHandler) {
        new HttpPostTask(request, mHandler, responseHandler).execute(url);
    }

    protected Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == 1) {
                Toast.makeText(BaseActivity.this,"请求接受失败",Toast.LENGTH_SHORT).show();

            } else if (msg.what == 2) {
                Toast.makeText(BaseActivity.this,"当前网络异常",Toast.LENGTH_SHORT).show();

            }
        }
    };
}
