package com.example.asus.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.eneity.News;
import com.example.asus.eneity.Vote;
import com.example.asus.mybigbang.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class VoteListViewAdapter extends BaseAdapter{
    ArrayList<Vote> list5=new ArrayList<Vote>();
    LayoutInflater inflater;
    public VoteListViewAdapter(Context context){
        inflater=LayoutInflater.from(context);
    }

    public void addDataVote(List<Vote> listinfo){
        list5.addAll(listinfo);
    }
    @Override
    public int getCount() {
        return list5.size();
    }

    @Override
    public Vote getItem(int position) {
        return list5.get(position);
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
            Vote vote=list5.get(position);
            holder.title.setText(vote.getTitle());
            holder.content.setText(vote.getContent());
            holder.date.setText(vote.getDate().getDate());

            DisplayImageOptions options=new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();

            ImageLoader.getInstance().displayImage(String.valueOf(vote.getImage().getFileUrl()),holder.image,options);
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
