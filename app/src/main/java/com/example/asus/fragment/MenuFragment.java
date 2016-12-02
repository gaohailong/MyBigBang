package com.example.asus.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.asus.adapter.ViewPagerAdapter;
import com.example.asus.mybigbang.R;

import java.util.ArrayList;

/**
 * PagerSlidingTabStrip和ViewPager的Fragment
 * 导航栏和滑动页面的Fragment
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {


    public MenuFragment() {
        // Required empty public constructor
    }

    View view;
    ViewPager pager;
    //PagerSlidingTabStrip 是与ViewPager搭配使用的一个在不同页面导航时可交互的指示器，可以实现滑动导航栏的效果。
    PagerSlidingTabStrip tabStrip;
    ViewPagerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_menu, container, false);
        pager= (ViewPager) view.findViewById(R.id.viewpager);
        tabStrip= (PagerSlidingTabStrip) view.findViewById(R.id.tabstrip);

        //用适配器把Fragment显示到viewpager中
        adapter=new ViewPagerAdapter(getFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(5);

        loadData();

        //得让布局加载完才能tabStrip.setViewPager(pager);把ViewPager添加到tabStrip中去
        tabStrip.setViewPager(pager);
        return view;



    }

    public void loadData(){
        ArrayList<String> list=new ArrayList<String>();
        list.add("新闻");
        list.add("公告");
        list.add("精品");
        list.add("相册");
        list.add("投票教程");
        list.add("故事");
        list.add("行程");

        ArrayList<Fragment> listf=new ArrayList<Fragment>();
        int i=0;
        //新闻Fragment
        NewsFragment mf=new NewsFragment();
        Bundle b1=new Bundle();
        b1.putInt("type",(i+1));
        mf.setArguments(b1);//用于Fragment进行传值
        listf.add(mf);
        //公告Fragment
        AnnouncementFragment af=new AnnouncementFragment();
        Bundle b2=new Bundle();
        b2.putInt("type",(i+2));
        af.setArguments(b2);//用于Fragment进行传值
        listf.add(af);
        //精品Fragment
        QualityFragment qf=new QualityFragment();
        Bundle b3=new Bundle();
        b3.putInt("type",(i+3));
        qf.setArguments(b3);//用于Fragment进行传值
        listf.add(qf);
        //相册Fragment
        PhotoFragment pf=new PhotoFragment();
        Bundle b4=new Bundle();
        b4.putInt("type",(i+4));
        pf.setArguments(b4);
        listf.add(pf);
        //投票教程Fragment
        NewsFragment mf5=new NewsFragment();
        Bundle b5=new Bundle();
        b5.putInt("type",(i+5));
        mf5.setArguments(b5);//用于Fragment进行传值
        listf.add(mf5);
        //故事Fragment
        StoryFragment sf=new StoryFragment();
        Bundle b6=new Bundle();
        b6.putInt("type",(i+6));
        sf.setArguments(b6);//用于Fragment进行传值
        listf.add(sf);
        //行程Fragment
        TravelFragment tf=new TravelFragment();
        Bundle b7=new Bundle();
        b7.putInt("type",(i+7));
        tf.setArguments(b7);
        listf.add(tf);

        //用adapter把Fragment和导航栏PagerSlidingTabStrip适配到ViewPager中
        adapter.addFragmentData(listf);
        adapter.addStrData(list);
        adapter.notifyDataSetChanged();

    }


}