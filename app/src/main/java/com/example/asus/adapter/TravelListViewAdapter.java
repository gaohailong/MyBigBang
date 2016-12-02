package com.example.asus.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.eneity.News;
import com.example.asus.eneity.Travel;
import com.example.asus.mybigbang.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * TravelFragment布局文件里listview的适配器
 * 实体类Travel的适配器
 * Created by Administrator on 2016/12/1.
 */
public class TravelListViewAdapter extends BaseAdapter{
    ArrayList<Travel> list7=new ArrayList<Travel>();

    LayoutInflater inflater;
    public TravelListViewAdapter(Context context){
        inflater=LayoutInflater.from(context);
    }

    public void addDataTravel(List<Travel> listinfo){
        list7.addAll(listinfo);
    }

    @Override
    public int getCount() {
        return list7.size();
    }

    @Override
    public Travel getItem(int position) {
        return list7.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=inflater.inflate(R.layout.inflater_listview_item1,null);
        }
        TextView date= (TextView) convertView.findViewById(R.id.travel_date);
        TextView content= (TextView) convertView.findViewById(R.id.travel_content);

        Travel travel=list7.get(position);
        date.setText(travel.getDate().getDate());
        content.setText(travel.getContent());
        return convertView;
    }
}
