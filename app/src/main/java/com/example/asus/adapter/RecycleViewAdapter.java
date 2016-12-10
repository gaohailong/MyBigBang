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
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyHolder>implements View.OnClickListener {

    ArrayList<Photo> list = new ArrayList<Photo>();
    LayoutInflater inflater;
    public RecycleViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void add(List<Photo> listinfo) {
        list.clear();
        list.addAll(listinfo);
        notifyItemInserted(list.size());
    }

    //创建新View，被LayoutManager所调用
    //从ViewHolder的代码中可以看到，执行onClick方法时会调用getPosition()将当前Item的位置回调给listener
    // getPosition()是ViewHolder的内置方法，可直接使用。
    //上面提到过，listener是设定到Adapter上的，所以Adapter就需要对外开放相关方法：

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View Itemview = inflater.inflate(R.layout.inflater_recyclerview_item, null);
        MyHolder h = new MyHolder(Itemview);

        return h;
    }
    /**
     * 设置Item点击监听
     * @param listener
     */
    MyItemClickListener mItemClickListener;
      public void setOnItemClickListener(MyItemClickListener listener){
             this.mItemClickListener = listener;
          }


    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        ImageLoader.getInstance().displayImage(list.get(position).getImage().getFileUrl(),holder.image);
        holder.text.setText(list.get(position).getTitle());
        holder.view.setTag(position);
        holder.view.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    /**
     * 点击监听
     */
    @Override
    public void onClick(View view) {
        if(mItemClickListener != null){
            mItemClickListener.OnItemClick(view,Integer.parseInt(view.getTag().toString()));
        }

    }



    //自定义的MyHolder，持有每个Item的的所有界面元素
    //因为ViewHolder我们可以拿到每个Item的根布局，
    // 所以如果我们为根布局设置单独的OnClick监听并将其开放给Adapter，
    // 那不就可以在组装RecyclerView时就能够设置ItemClickListener，
    // 只不过这个Listener不是设置到RecyclerView上而是设置到Adapter。
    class MyHolder extends RecyclerView.ViewHolder{
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
    //下面要考虑的就是怎样把listener传递进来。设定了监听点击事件的Interface：MyItemClickListener:
    public interface MyItemClickListener {
        public void OnItemClick(View view,int position);
    }


}

