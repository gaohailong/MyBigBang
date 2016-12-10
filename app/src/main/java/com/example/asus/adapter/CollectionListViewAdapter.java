package com.example.asus.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.eneity.Collection;
import com.example.asus.eneity.News;
import com.example.asus.mybigbang.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * CollectionActivity布局文件里listview的适配器
 * Created by Administrator on 2016/12/3.
 */
public class CollectionListViewAdapter extends BaseAdapter{
    ArrayList<Collection> list=new ArrayList<Collection>();

    LayoutInflater inflater;
    public CollectionListViewAdapter(Context context){
        inflater=LayoutInflater.from(context);
    }

    public void addDataCollection(List<Collection> listinfo){
        list.clear();
        list.addAll(listinfo);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Collection getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyHolder holder;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.inflater_collectionlistview_item,null);
            holder=new MyHolder();
            holder.title= (TextView) convertView.findViewById(R.id.colistview_title);
            holder.content= (TextView) convertView.findViewById(R.id.colistview_content);
            holder.date= (TextView) convertView.findViewById(R.id.colistview_date);
            holder.image= (ImageView) convertView.findViewById(R.id.colistview_image);
            convertView.setTag(holder);
        }else{
            holder= (MyHolder) convertView.getTag();
        }

            Collection collection=list.get(position);
            holder.title.setText(collection.getTitle());
            holder.content.setText(collection.getContent());
            holder.date.setText(collection.getDate().getDate());

            DisplayImageOptions options=new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();

            ImageLoader.getInstance().displayImage(String.valueOf(collection.getImage().getFileUrl()),holder.image,options);

        return convertView;
    }


    class MyHolder{
        TextView title;
        TextView content;
        TextView date;
        ImageView image;

    }
}
