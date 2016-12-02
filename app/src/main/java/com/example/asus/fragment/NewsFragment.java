package com.example.asus.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.asus.adapter.NewsListViewAdapter;
import com.example.asus.eneity.News;
import com.example.asus.mybigbang.BigBangActivity;
import com.example.asus.mybigbang.DetailBrowerActivity;
import com.example.asus.mybigbang.R;
import com.example.asus.view.ListViewForScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * PullToRefreshScrollView和listview的Fragment
 * 新闻数据界面
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2,AdapterView.OnItemClickListener{


    public NewsFragment() {
        // Required empty public constructor
    }

    RequestQueue queue;
    int type;
    ListViewForScrollView newslistview;
    PullToRefreshScrollView newsscrollview;
    NewsListViewAdapter adapter1;
    View view1;
    TextView bigbangtextview;
    LinearLayout newslayout;
    ArrayList<News> list1;
    int a;
    int flag=0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        queue= Volley.newRequestQueue(getActivity());
        type=getArguments().getInt("type",-1);
        Toast.makeText(getActivity(), type+"-----", Toast.LENGTH_SHORT).show();
        view1=inflater.inflate(R.layout.fragment_news, container, false);


        Bundle b = getArguments();
         a = b.getInt("type");
         // scrollview内的线性布局
        newslayout = (LinearLayout)view1.findViewById(R.id.news_layout);
        //设置当前焦点，防止打开Activity出现在ListView位置问题
        newslayout.setFocusable(true);
        newslayout.setFocusableInTouchMode(true);
        newslayout.requestFocus();
        //找到控件   PullToRefreshScrollView
        newsscrollview= (PullToRefreshScrollView) view1.findViewById(R.id.news_scrollview);
        bigbangtextview= (TextView) view1.findViewById(R.id.bigbang);
        newslistview= (ListViewForScrollView) view1.findViewById(R.id.news_listview);
        //scrollview只允许上拉
        newsscrollview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        newsscrollview.setOnRefreshListener(this);
        newslistview.setOnItemClickListener(this);

        // 在创建视图的时候调用bigbangtextview的监听
        getBigbangtextview();

        adapter1=new NewsListViewAdapter(getActivity());
        newslistview.setAdapter(adapter1);
        //调用查询新闻表里的方法，一进来就查询显示5条数据
        getData1();

        return view1;
    }

    //bigbang TextView的监听，百度百科
    public TextView getBigbangtextview() {
        bigbangtextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), BigBangActivity.class);
                startActivity(intent);
            }
        });
        return bigbangtextview;
    }

    //scrollview的下拉刷新
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
    }

    //scrollview的上拉加载
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

        flag++;
        getData1();
        newsscrollview.onRefreshComplete();

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        if(a == 1){
            News info=adapter1.getItem(position);
            Toast.makeText(getActivity(), info.getTitle()+""+position, Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getActivity(), DetailBrowerActivity.class);
            i.putExtra("a",a);
            i.putExtra("info",info);
            startActivity(i);
        }

    }

    //查询新闻表里的数据
    public void getData1(){
        list1=new ArrayList<>();
        BmobQuery<News> query = new BmobQuery<News>();
        query.setLimit(5);
        //setSKip方法可以做到跳过查询的前多少条数据来实现分页查询的功能。默认情况下Skip的值为10。
        // 忽略前10条数据（即第一页数据结果）
        query.setSkip(flag*5);
        query.findObjects(new FindListener<News>() {
            @Override
            public void done(List<News> object, BmobException e) {
                if (e == null) {
//                    Toast.makeText(MainActivity.this, "查询成功：共" + object.size() + "条数据。", Toast.LENGTH_SHORT).show();
                    for (News news: object) {
                        //获得New里的信息
                        String title=news.getTitle();
                        String content=news.getContent();
                        BmobDate date=news.getDate();
                        BmobFile image=news.getImage();
                        //获得数据的objectId信息
                        news.getObjectId();
                        //获得createdAt数据创建时间（注意是：createdAt，不是createAt）
                        news.getCreatedAt();
                        //把所有的信息放到list集合里
                        news.setTitle(title);
                        news.setContent(content);
                        news.setDate(date);
                        news.setImage(image);
                        list1.add(news);
                    }
                   //通过adapter是配到列表上
                    if(a == 1){
                    Toast.makeText(getActivity(), list1.size()+"------------", Toast.LENGTH_SHORT).show();
                        adapter1.addDataNews(list1);
                        adapter1.notifyDataSetChanged();
                    }
//                    Toast.makeText(getActivity(), list.size()+"", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "查询失败！"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("TAG",e.getErrorCode()+","+e.getMessage());
                }
            }
        });
    }




}
