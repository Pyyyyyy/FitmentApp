package com.example.app.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.app.R;
import com.example.app.Tools.GridViewAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class wallet extends Activity {

    private GridView mGridView;
    private int[] images = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.l};
    private String[] titles = {"个人资料","积分","订单","消费记录","充值","提现","意见反馈","联系客服"};
    private String[] descs = {"完善信息","20分","查看状态","(获得积分)","从银行卡充值到余额","将余额提现到银行卡","改善用户体验","及时解决您的问题"};
    private List<Map<String,Object>> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        mGridView.setAdapter(new GridViewAdapter(this, mList));
        mGridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), "position: "+position + " id: "+id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        mList.clear();
        for (int i = 0; i < images.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("images", images[i]);
            map.put("titles", titles[i]);
            map.put("descs", descs[i]);
            mList.add(map);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_wallet);
        mGridView = (GridView) findViewById(R.id.gridview);
    }
}
