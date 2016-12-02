package com.example.asus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.eneity.Photo;
import com.example.asus.mybigbang.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Administrator on 2016/11/29.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyHolder> {

    ArrayList<Photo> list = new ArrayList<Photo>();
    LayoutInflater inflater;

    public RecycleViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void add(ArrayList<Photo> listinfo) {
        list.addAll(listinfo);
        notifyItemInserted(list.size());
    }

    public void onItemMove(int from, int to) {
        Collections.swap(list, from, to);
        notifyItemMoved(from, to);
    }


    //创建新View，被LayoutManager所调用
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.inflater_recyclerview_item, null);
        MyHolder h = new MyHolder(view);
        return h;
    }


    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnItemClick(position, list.get(position));
            }
        });
//        holder.view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener1.OnItemLongClick(position, list.get(position));
//            }
//        });
    }

    MyItemListener listener;

    public void setOnItemClickListener(MyItemListener listener) {
        if (listener != null) {
            this.listener = listener;
        }
    }

    MyItemLongListener listener1;

    public void setOnItemLongClickListener(MyItemLongListener listener) {
        if (listener != null) {
            this.listener1 = listener;
        }
    }

    @Override
    public int getItemCount() {
        return 25;
    }

    //自定义的MyHolder，持有每个Item的的所有界面元素
    class MyHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        View view = null;

        public MyHolder(View itemView) {
            super(itemView);
            view = itemView;
            image = (ImageView) itemView.findViewById(R.id.image);
            text = (TextView) itemView.findViewById(R.id.tv_context);
        }
    }

    public interface MyItemListener {
        public void OnItemClick(int position, Photo str);
    }

    public interface MyItemLongListener {
        public void OnItemLongClick(int position, Photo str);
    }

}

