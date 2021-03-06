package com.example.app.main;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.app.Fragment.FragmenMy;
import com.example.app.Fragment.FragmenWorker;
import com.example.app.Fragment.FragmenZxcl;
import com.example.app.R;

import com.example.app.Fragment.FragmenHome;

import com.example.app.Tools.wallet;
import com.example.app.Worker.WorkerActivity;



public class MainActivity extends AppCompatActivity implements OnClickListener {
    private LinearLayout layoutTab01;//
    private LinearLayout layoutTab02;//
    private LinearLayout layoutTab03;//
    private LinearLayout layoutTab04;//


    private FragmentManager manager;
    private FragmentTransaction transaction;

    private FragmenHome home;
    private FragmenZxcl zxcl;
    private FragmenWorker worker;
    private FragmenMy my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        NavigationView navView  = (NavigationView) findViewById(R.id.nav_view);
        /*
        Button image4 = (Button) findViewById(R.id.image4);
        image4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,zhuangxiuqianActivity.class);
                startActivity(intent);
            }
        });

        Button image5 = (Button) findViewById(R.id.image5);
        image5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,zhuangxiuzhongActivity.class);
                startActivity(intent);

            }
        });*/

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_wallet:
                        Intent intent = new Intent(MainActivity.this,wallet.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_collection:
                        Toast.makeText(MainActivity.this,"你点击了我的收藏",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_follow:
                        Toast.makeText(MainActivity.this,"你点击了我的关注 ",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_setting:
                        Toast.makeText(MainActivity.this,"你点击了设置",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_appointment:
                        Toast.makeText(MainActivity.this,"你点击了我的预约",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_news:
                        Toast.makeText(MainActivity.this,"你点击了我的消息",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_order:
                        Toast.makeText(MainActivity.this,"你点击了我的订单",Toast.LENGTH_SHORT).show();
                        break;



                }


                return true;
            }


        });

        initView();
        initEvent();
        setSelect(0);


    }
    private void setSelect(int i)
    {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        hideFragment(transaction);

        switch (i)
        {
            case 0:
                //imageView01.setImageResource(R.drawable.tab_weixin_pressed);
                if(home == null)
                {
                    home = new FragmenHome();
                    transaction.add(R.id.fragment_ui,home);
                }
                else
                {
                    transaction.show(home);
                }
                break;
            case 1:
                //imageView02.setImageResource(R.drawable.tab_address_pressed);
                if(zxcl == null)
                {
                    zxcl = new FragmenZxcl();
                    transaction.add(R.id.fragment_ui,zxcl);
                }
                else
                {
                    transaction.show(zxcl);
                }
                break;
            case 2:

                if(worker == null)
                {
                    worker = new FragmenWorker();
                    transaction.add(R.id.fragment_ui,worker);
                }
                else
                {
                    transaction.show(worker);
                }
                break;
            case 3:

                if(my == null)
                {
                    my = new FragmenMy();
                    transaction.add(R.id.fragment_ui,my);
                }
                else
                {
                    transaction.show(my);
                }
                break;


            default:
                break;
        }

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction2)
    {
        // TODO Auto-generated method stub
        if(home != null)
        {
            transaction2.hide(home);
        }

        if(zxcl != null)
        {
            transaction2.hide(zxcl);
        }
        if(worker != null)
        {
            transaction2.hide(worker);
        }
        if(my != null)
        {
            transaction2.hide(my);
        }
    }

    private void initEvent()
    {
        layoutTab01.setOnClickListener(this);
        layoutTab02.setOnClickListener(this);
        layoutTab03.setOnClickListener(this);
        layoutTab04.setOnClickListener(this);


    }

    private void initView()
    {
        layoutTab01=(LinearLayout)findViewById(R.id.tab01);
        layoutTab02=(LinearLayout)findViewById(R.id.tab02);
        layoutTab03=(LinearLayout)findViewById(R.id.tab03);
        layoutTab04=(LinearLayout)findViewById(R.id.tab04);


    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.tab01:
                setSelect(0);
                break;
            case R.id.tab02:
                setSelect(1);
                break;
            case R.id.tab03:
                setSelect(2);
                break;
            case R.id.tab04:
                setSelect(3);
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
