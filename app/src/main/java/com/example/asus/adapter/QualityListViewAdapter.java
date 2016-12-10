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
import com.example.asus.eneity.Quality;
import com.example.asus.mybigbang.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * MenuBrowerFragment布局文件里自定义的listview的适配器
 * 实体类Quality的适配器
 * Created by Administrator on 2016/11/29.
 */
public class QualityListViewAdapter extends BaseAdapter {
    ArrayList<Quality> list3=new ArrayList<Quality>();

    LayoutInflater inflater;
    public QualityListViewAdapter(Context context){
        inflater=LayoutInflater.from(context);
    }

    public void addDataQuality(ArrayList<Quality> listinfo1){
//        list3.clear();//不能clear，如果clear后头5条数据会替换前5条数据
        list3.addAll(listinfo1);
    }

    public void addDataToHeader(ArrayList<Quality> listinfo){
        list3.addAll(0,listinfo);
    }
    public void addDataToFooter(ArrayList<Quality> listinfo){
        list3.addAll(listinfo);
    }

    @Override
    public int getCount() {
        return list3.size();
    }

    @Override
    public Quality getItem(int position) {
        return list3.get(position);
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
        try{
            Quality quality=list3.get(position);
            holder.title.setText(quality.getTitle());
            holder.content.setText(quality.getContent());
            holder.date.setText(quality.getDate().getDate());

            DisplayImageOptions options=new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();

            ImageLoader.getInstance().displayImage(String.valueOf(quality.getImage().getFileUrl()),holder.image,options);
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
