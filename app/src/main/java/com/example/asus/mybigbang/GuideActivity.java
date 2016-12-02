package com.example.asus.mybigbang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.asus.adapter.ViewPageAdapter;
import com.example.asus.base.BaseActivity;

import java.util.ArrayList;

public class GuideActivity extends BaseActivity {

    ViewPager viewPager;
    ViewPageAdapter adapter;
    Button item3_btn;
    ArrayList<ImageView> point =new ArrayList<ImageView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guide);
        super.onCreate(savedInstanceState);
        SharedPreferences sp=getSharedPreferences("Manager",MODE_PRIVATE);
        boolean flag=sp.getBoolean("isFirst",false);
        if (flag){
            Intent i=new Intent(GuideActivity.this,WelcomeActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void init() {
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        adapter=new ViewPageAdapter();
        viewPager.setAdapter(adapter);
        View v1= LayoutInflater.from(this).inflate(R.layout.inflater_viewpager_item1,null);
        View v2= LayoutInflater.from(this).inflate(R.layout.inflater_viewpager_item2,null);
        View v3= LayoutInflater.from(this).inflate(R.layout.inflater_viewpager_item3,null);
        item3_btn= (Button) v3.findViewById(R.id.item3_btn);
        point.add((ImageView) findViewById(R.id.image1));
        point.add((ImageView) findViewById(R.id.image2));
        point.add((ImageView) findViewById(R.id.image3));

        ArrayList<View> list=new ArrayList<View>();
        list.add(v1);
        list.add(v2);
        list.add(v3);
        adapter.addData(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void Listener() {
        item3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(GuideActivity.this,WelcomeActivity.class);
                startActivity(i);
                finish();
                SharedPreferences sp=getSharedPreferences("Manager",MODE_PRIVATE);
                SharedPreferences.Editor e=sp.edit();
                e.putBoolean("isFirst",true);
                e.commit();
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i=0;i<point.size();i++){
                    if (i==position){
                        ImageView view=point.get(i);
                        view.setBackgroundResource(R.mipmap.aqi_grade);
                    }else {
                        ImageView view=point.get(i);
                        view.setBackgroundResource(R.mipmap.aqi_grade_0);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }



    @Override
    public void loadData() {

    }


}
