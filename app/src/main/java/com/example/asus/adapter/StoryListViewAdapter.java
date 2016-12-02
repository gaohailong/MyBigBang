package com.example.asus.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.eneity.Quality;
import com.example.asus.eneity.Story;
import com.example.asus.mybigbang.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * MenuBrowerFragment布局文件里自定义的listview的适配器
 * 实体类Story的适配器
 * Created by Administrator on 2016/11/30.
 */
public class StoryListViewAdapter extends BaseAdapter{

    ArrayList<Story> list6=new ArrayList<Story>();
    LayoutInflater inflater;
    public StoryListViewAdapter(Context context){
        inflater=LayoutInflater.from(context);
    }

    public void addDataStory(ArrayList<Story> listinfo1){
//        list6.clear();//不能clear，如果clear后头5条数据会替换前5条数据
        list6.addAll(listinfo1);
    }

    @Override
    public int getCount() {
        return list6.size();
    }

    @Override
    public Story getItem(int position) {
        return list6.get(position);
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

        Story story=list6.get(position);
        holder.title.setText(story.getTitle());
        holder.content.setText(story.getContent());
        holder.date.setText(story.getDate().getDate());

        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();

        ImageLoader.getInstance().displayImage(String.valueOf(story.getImage().getFileUrl()),holder.image,options);

        return convertView;
    }



    class MyHolder{
        TextView title;
        TextView content;
        TextView date;
        ImageView image;

    }


}
