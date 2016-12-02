package com.example.asus.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * MenuFragment布局文件里ViewPager的适配器
 * Created by Administrator on 2016/11/14.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<String> liststr=new ArrayList<String>();
    ArrayList<Fragment> listf=new ArrayList<Fragment>();

    public void addStrData(ArrayList<String> liststr){
        this.liststr.addAll(liststr);
    }
    public void addFragmentData(ArrayList<Fragment> listf){
        this.listf.addAll(listf);
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return liststr.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return listf.get(position);
    }

    @Override
    public int getCount() {
        return listf.size();
    }
}
