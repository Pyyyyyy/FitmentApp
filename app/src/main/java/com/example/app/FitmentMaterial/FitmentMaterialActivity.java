package com.example.app.FitmentMaterial;
/*
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;

import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.Worker.WorkerActivity;
import com.example.app.http.BaseActivity;
import com.example.app.http.CommonRequest;
import com.example.app.http.CommonResponse;
import com.example.app.http.ResponseHandler;
import com.example.app.main.MainActivity;
import com.example.app.Tools.wallet;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class FitmentMaterialActivity extends BaseActivity {

   // private String URL_FITMENT = "http://w2062389t3.iask.in:42216/FitmentApp/FitmentMaterialServlet";
    private SwipeRefreshLayout swipeRefresh;

    private Material[] materials = {new Material("木板 ￥10", R.drawable.tumu1), new Material("木板 ￥20", R.drawable.tumu2),
            new Material("木板 ￥30", R.drawable.tumu3), new Material("油漆 ￥10", R.drawable.youqi1),
            new Material ("油漆 ￥20", R.drawable.youqi2), new Material("油漆 ￥30", R.drawable.youqi3),
            new Material("水泥 ￥10", R.drawable.nimu1), new Material("水泥 ￥20", R.drawable.nimu2),
            new Material("水泥 ￥30", R.drawable.nimu3), new Material("水泥 ￥40", R.drawable.nimu4),
            new Material("厨房 ￥10", R.drawable.chuyu1),new Material("浴室 ￥30", R.drawable.chuyu2)};

    private List<Material>materialList = new ArrayList<>();
    private MaterialAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fitment_material_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }

       //Button button01 = (Button) findViewById(R.id.button_shoppingcar);
        TextView add = (TextView) findViewById(R.id.add);
        ImageView cancel = (ImageView) findViewById(R.id.cancel);
        //NavigationView navView  = (NavigationView) findViewById(R.id.nav_view);

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(FitmentMaterialActivity.this,FitmentAddActivity.class);
                startActivity(intent);
            }
        });*/
/*
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FitmentMaterialActivity.this,ShoppingCar.class);
                startActivity(intent);
            }
        });
*/
/*
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
*/
/*
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_wallet:
                        Intent intent = new Intent(FitmentMaterialActivity.this,wallet.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_collection:
                        Toast.makeText(FitmentMaterialActivity.this,"你点击了我的收藏",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_follow:
                        Toast.makeText(FitmentMaterialActivity.this,"你点击了我的关注 ",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_setting:
                        Toast.makeText(FitmentMaterialActivity.this,"你点击了设置",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_appointment:
                        Toast.makeText(FitmentMaterialActivity.this,"你点击了我的预约",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_news:
                        Toast.makeText(FitmentMaterialActivity.this,"你点击了我的消息",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_order:
                        Toast.makeText(FitmentMaterialActivity.this,"你点击了我的订单",Toast.LENGTH_SHORT).show();
                        break;

                }


                return true;
            }


        });

*/ /*
        initMaterials();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MaterialAdapter(materialList);
        recyclerView.setAdapter(adapter);

        //fitmentMaterial();

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMaterial();
            }
        });



    }*/
/*
    public void fitmentMaterial(){
        final CommonRequest request = new CommonRequest();
        sendHttpPostRequest(URL_FITMENT,request,new ResponseHandler(){
            @Override
            public void success(CommonResponse response) {
                materialList.clear();
                ArrayList<HashMap<String, String>> materials = response.getDataList();
                for(HashMap<String,String> material:materials){
                    byte[] bytes = Base64.decode(material.get("picture"), Base64.DEFAULT);
                    Material material1 = new Material(material.get("name").toString(), BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                    materialList.add(material1);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(FitmentMaterialActivity.this,failMsg,Toast.LENGTH_SHORT).show();
            }

        });

    }
*//*
    private void refreshMaterial(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //fitmentMaterial();
                        initMaterials();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initMaterials() {
        materialList.clear();
        for (int i = 0; i < 12; i++) {

            //Random random = new Random();
            //int index = random.nextInt(materials.length);
            materialList.add(materials[i]);
        }
    }
}
*/



import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.app.CategoryBean;
import com.example.app.HomeAdapter;
import com.example.app.MenuAdapter;
import com.example.app.R;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class FitmentMaterialActivity extends AppCompatActivity {

    private List<String> menuList = new ArrayList<>();
    private List<CategoryBean.DataBean> homeList = new ArrayList<>();
    private List<Integer> showTitle;

    private ListView lv_menu;
    private ListView lv_home;

    private MenuAdapter menuAdapter;
    private HomeAdapter homeAdapter;
    private int currentItem;

    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fitment_material_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        TextView add = (TextView) findViewById(R.id.add);
        ImageView cancel = (ImageView) findViewById(R.id.cancel);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(FitmentMaterialActivity.this,FitmentAddActivity.class);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Fresco.initialize(this);
        initView();
        loadData();
    }


    private void loadData() {

        String json = getJson(this, "category.json");
        CategoryBean categoryBean = JSONObject.parseObject(json, CategoryBean.class);
        showTitle = new ArrayList<>();
        for (int i = 0; i < categoryBean.getData().size(); i++) {
            CategoryBean.DataBean dataBean = categoryBean.getData().get(i);
            menuList.add(dataBean.getModuleTitle());
            showTitle.add(i);
            homeList.add(dataBean);
        }
        tv_title.setText(categoryBean.getData().get(0).getModuleTitle());

        menuAdapter.notifyDataSetChanged();
        homeAdapter.notifyDataSetChanged();
    }

    private void initView() {
        lv_menu = (ListView) findViewById(R.id.lv_menu);
        tv_title = (TextView) findViewById(R.id.tv_titile);
        lv_home = (ListView) findViewById(R.id.lv_home);
        menuAdapter = new MenuAdapter(this, menuList);
        lv_menu.setAdapter(menuAdapter);

        homeAdapter = new HomeAdapter(this, homeList);
        lv_home.setAdapter(homeAdapter);

        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuAdapter.setSelectItem(position);
                menuAdapter.notifyDataSetInvalidated();
                tv_title.setText(menuList.get(position));
                lv_home.setSelection(showTitle.get(position));
            }
        });


        lv_home.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int scrollState;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.scrollState = scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    return;
                }
                int current = showTitle.indexOf(firstVisibleItem);
//				lv_home.setSelection(current);
                if (currentItem != current && current >= 0) {
                    currentItem = current;
                    tv_title.setText(menuList.get(currentItem));
                    menuAdapter.setSelectItem(currentItem);
                    menuAdapter.notifyDataSetInvalidated();
                }
            }
        });
    }

    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


}
