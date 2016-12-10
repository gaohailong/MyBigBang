package com.example.asus.mybigbang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.adapter.NewsListViewAdapter;
import com.example.asus.base.BaseActivity;
import com.example.asus.fragment.MenuFragment;
import com.example.asus.fragment.SettingFragment;

import cn.bmob.v3.Bmob;

public class MainActivity extends BaseActivity {

    MenuFragment fragment;
    ImageView expanded_menu;
    ImageView setting;
    TextView tv_menu;
    TextView tv_setting;
    ImageView title;
    ImageView add_image;
    SettingFragment settingFragment;
    Fragment currentFragment;
    NewsListViewAdapter adapter;

    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);



    }

    @Override
    public void init() {

        //Bmob默认初始化
        Bmob.initialize(this,"441eea9b8abf7a3bcfc47a8f8fa5dad0");
        title = (ImageView) findViewById(R.id.main_title);
        //添加一个Fragment到主页面
        fragment = new MenuFragment();
        settingFragment = new SettingFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container,fragment,fragment.getClass().getName());
        currentFragment = fragment;
        ft.commit();

        expanded_menu= (ImageView) findViewById(R.id.expanded_menu);
        setting= (ImageView) findViewById(R.id.setting);
        add_image= (ImageView) findViewById(R.id.add_image);
        tv_menu= (TextView) findViewById(R.id.tv_menu);
        tv_setting= (TextView) findViewById(R.id.tv_setting);

        adapter=new NewsListViewAdapter(this);



    }

    @Override
    public void Listener() {
        expanded_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smartReplaceFragment(fragment);
                title.setImageResource(R.mipmap.dongtai);
                expanded_menu.setBackgroundResource(R.mipmap.menu11);
                setting.setBackgroundResource(R.mipmap.setting12);
                tv_menu.setTextColor(Color.parseColor("#ff5c6c"));
                tv_setting.setTextColor(Color.WHITE);

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smartReplaceFragment(settingFragment);
                title.setImageResource(R.mipmap.shezhi);
                setting.setBackgroundResource(R.mipmap.setting11);
                expanded_menu.setBackgroundResource(R.mipmap.menu1);
                tv_setting.setTextColor(Color.parseColor("#ff5c6c"));
                tv_menu.setTextColor(Color.WHITE);
            }
        });

        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ContributeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loadData() {

    }

    public void smartReplaceFragment(Fragment toFragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        //先隐藏
        if (currentFragment!=null){
            ft.hide(currentFragment);
        }
        //判断fm中是否有toFragment，没有的话添加进去，有的话直接显示
        if (fm.findFragmentByTag(toFragment.getClass().getName())==null){
            ft.add(R.id.container,toFragment,toFragment.getClass().getName());
        }
        //显示
        ft.show(toFragment);
        //提交
        ft.commit();
        currentFragment=toFragment;
    }


    // 重写Activity中onKeyDown（）方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 当keyCode等于退出事件值时
            ToQuitTheApp();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    //封装ToQuitTheApp方法
    private void ToQuitTheApp() {
        if (isExit) {
            // ACTION_MAIN with category CATEGORY_HOME 启动主屏幕
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);// 使虚拟机停止运行并退出程序
        } else {
            isExit = true;
            Toast.makeText(MainActivity.this, "再按一次退出APP", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 3000);// 3秒后发送消息
        }
    }

    //创建Handler对象，用来处理消息
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {//处理消息
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            isExit = false;
        }
    };



}
