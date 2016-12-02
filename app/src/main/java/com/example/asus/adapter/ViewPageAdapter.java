package com.example.asus.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/19.
 */
public class ViewPageAdapter extends PagerAdapter {
    ArrayList<View> list=new ArrayList<View>();
    public void addData(ArrayList<View> listinfo){
       list.addAll(listinfo);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=list.get(position%list.size());//根据position拿到当前的view视图
//        if (view.getParent()!=null){
//            container.removeView(view);
//        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view=list.get(position%list.size());
        container.removeView(view);
    }

}
