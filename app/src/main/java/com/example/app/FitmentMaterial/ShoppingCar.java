package com.example.app.FitmentMaterial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ShoppingCar extends AppCompatActivity implements CartAdapter.RefreshPriceInterface ,View.OnClickListener{

    private ListView listView;
    private CheckBox cb_check_all;
    private TextView tv_total_price;
    private TextView tv_delete;
    private TextView tv_go_to_pay;

    private CartAdapter adapter;

    private double totalPrice = 0.00;
    private int totalCount = 0;
    private List<HashMap<String,String>> goodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppingcar);

        initDate();
    }

    //控制价格展示
    private void priceControl(Map<String, Integer> pitchOnMap){
        totalCount = 0;
        totalPrice = 0.00;
        for(int i=0;i<goodsList.size();i++){
            if(pitchOnMap.get(goodsList.get(i).get("id"))==1){
                totalCount=totalCount+Integer.valueOf(goodsList.get(i).get("count"));
                double goodsPrice=Integer.valueOf(goodsList.get(i).get("count"))*Double.valueOf(goodsList.get(i).get("price"));
                totalPrice=totalPrice+goodsPrice;
            }
        }
        tv_total_price.setText("￥ "+totalPrice);
        tv_go_to_pay.setText("付款("+totalCount+")");
    }

    @Override
    public void refreshPrice(Map<String, Integer> pitchOnMap) {
        priceControl(pitchOnMap);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.all_chekbox:
                AllTheSelected();
                break;
            case R.id.tv_go_to_pay:
                if(totalCount<=0){
                    Toast.makeText(this,"请选择要付款的商品~",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(this,"钱就是另一回事了~",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_delete:
                if(totalCount<=0){
                    Toast.makeText(this,"请选择要删除的商品~",Toast.LENGTH_SHORT).show();
                    return;
                }
                checkDelete(adapter.getPitchOnMap());
                break;
        }
    }

    //删除操作
    private void checkDelete(Map<String,Integer> map){
        List<HashMap<String,String>> waitDeleteList=new ArrayList<>();
        Map<String,Integer> waitDeleteMap =new HashMap<>();
        for(int i=0;i<goodsList.size();i++){
            if(map.get(goodsList.get(i).get("id"))==1){
                waitDeleteList.add(goodsList.get(i));
                waitDeleteMap.put(goodsList.get(i).get("id"),map.get(goodsList.get(i).get("id")));
            }
        }
        goodsList.removeAll(waitDeleteList);
        map.remove(waitDeleteMap);
        priceControl(map);
        adapter.notifyDataSetChanged();
    }

    //全选或反选
    private void AllTheSelected(){
        Map<String,Integer> map=adapter.getPitchOnMap();
        boolean isCheck=false;
        boolean isUnCheck=false;
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if(Integer.valueOf(entry.getValue().toString())==1)isCheck=true;
            else isUnCheck=true;
        }
        if(isCheck==true&&isUnCheck==false){//已经全选,做反选
            for(int i=0;i<goodsList.size();i++){
                map.put(goodsList.get(i).get("id"),0);
            }
            cb_check_all.setChecked(false);
        }else if(isCheck==true && isUnCheck==true){//部分选择,做全选
            for(int i=0;i<goodsList.size();i++){
                map.put(goodsList.get(i).get("id"),1);
            }
            cb_check_all.setChecked(true);
        }else if(isCheck==false && isUnCheck==true){//一个没选,做全选
            for(int i=0;i<goodsList.size();i++){
                map.put(goodsList.get(i).get("id"),1);
            }
            cb_check_all.setChecked(true);
        }
        priceControl(map);
        adapter.setPitchOnMap(map);
        adapter.notifyDataSetChanged();
    }

    private void initView(){
        listView = (ListView) findViewById(R.id.listview);
        cb_check_all = (CheckBox) findViewById(R.id.all_chekbox);
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        tv_go_to_pay = (TextView) findViewById(R.id.tv_go_to_pay);
        tv_go_to_pay.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        cb_check_all.setOnClickListener(this);

        adapter=new CartAdapter(this,goodsList);
        adapter.setRefreshPriceInterface(this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initDate(){
        goodsList=new ArrayList<>();
        for(int i=0;i<10;i++){
            HashMap<String,String> map=new HashMap<>();
            map.put("id",(new Random().nextInt(10000)%(10000-2900+2900) + 2900)+"");
            map.put("name","购物车里的第"+(i+1)+"件商品");
            map.put("type",(i+20)+"码");
            map.put("price",(new Random().nextInt(100)%(100-29+29) + 29)+"");
            map.put("count",(new Random().nextInt(10)%(10-1+1) + 1)+"");
            goodsList.add(map);
        }

        initView();
    }
}
