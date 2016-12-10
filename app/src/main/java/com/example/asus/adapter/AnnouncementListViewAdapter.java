package com.example.asus.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.eneity.Announcement;
import com.example.asus.mybigbang.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 *
 * MenuBrowerFragment布局文件里listview的适配器
 * 实体类Announcement的适配器
 * Created by Administrator on 2016/11/28.
 */
public class AnnouncementListViewAdapter extends BaseAdapter{

    ArrayList<Announcement> list2=new ArrayList<Announcement>();
    LayoutInflater inflater;
    public AnnouncementListViewAdapter(Context context){
        inflater=LayoutInflater.from(context);
    }

    public void addDataAnnouncement(ArrayList<Announcement> listinfo2){
        list2.addAll(listinfo2);
    }


    public void addDataToHeader(ArrayList<Announcement> listinfo){
        list2.addAll(0,listinfo);
    }
    public void addDataToFooter(ArrayList<Announcement> listinfo){
        list2.addAll(listinfo);
    }

    @Override
    public int getCount() {
        return list2.size();
    }

    @Override
    public Announcement getItem(int position) {
        return list2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyHolder holder;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.inflater_listview_item,null);
            holder=new MyHolder();
            holder.title= (TextView) convertView.findViewById(R.id.lv_title);
            holder.content= (TextView) convertView.findViewById(R.id.lv_content);
            holder.date= (TextView) convertView.findViewById(R.id.lv_date);
            holder.image= (ImageView) convertView.findViewById(R.id.lv_image);
            convertView.setTag(holder);
        }else{
            holder= (MyHolder) convertView.getTag();
        }
        //防止不够五条数据时报空指针
        try {
            Announcement announcement=list2.get(position);
            holder.title.setText(announcement.getTitle());
            holder.content.setText(announcement.getContent());
            holder.date.setText(announcement.getDate().getDate());

            DisplayImageOptions options=new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();

            ImageLoader.getInstance().displayImage(String.valueOf(announcement.getImage().getFileUrl()),holder.image,options);
        }catch (Exception e){
            e.printStackTrace();
        }



        return convertView;
    }


    class MyHolder{
        TextView title;
        TextView content;
        TextView date;
        ImageView image;
    }


}
