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
import com.example.asus.eneity.News;
import com.example.asus.mybigbang.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * NewsFragment布局文件里listview的适配器
 * 实体类News的适配器
 * Created by Administrator on 2016/11/15.
 */
public class NewsListViewAdapter extends BaseAdapter{
    ArrayList<News> list1=new ArrayList<News>();

    LayoutInflater inflater;
    public NewsListViewAdapter(Context context){
        inflater=LayoutInflater.from(context);
    }

    public void addDataNews(List<News> listinfo1){
//        list1.clear();//不能clear，如果clear后头5条数据会替换前5条数据
        list1.addAll(listinfo1);
    }

    public void addDataToHeader(ArrayList<News> listinfo){
        list1.addAll(0,listinfo);
    }
    public void addDataToFooter(ArrayList<News> listinfo){
        list1.addAll(listinfo);
    }

    @Override
    public int getCount() {
        return list1.size();
    }

    @Override
    public News getItem(int position) {
        return list1.get(position);
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

        News info=list1.get(position);
        holder.title.setText(info.getTitle());
        holder.content.setText(info.getContent());
        holder.date.setText(info.getDate().getDate());

        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();

        ImageLoader.getInstance().displayImage(String.valueOf(info.getImage().getFileUrl()),holder.image,options);

        return convertView;
    }



    class MyHolder{
        TextView title;
        TextView content;
        TextView date;
        ImageView image;

    }


}
