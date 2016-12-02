package com.example.asus.fragment;


import android.content.Intent;
import android.os.Bundle;
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
import com.example.asus.adapter.StoryListViewAdapter;
import com.example.asus.eneity.Story;
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
 * A simple {@link Fragment} subclass.
 */
public class StoryFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2,AdapterView.OnItemClickListener{


    public StoryFragment() {
        // Required empty public constructor
    }

    RequestQueue queue;
    int type;
    ListViewForScrollView storylistview;
    PullToRefreshScrollView storyscrollview;
    StoryListViewAdapter adapter6;
    View view6;
    TextView bigbangtextview;
    LinearLayout storylayout;
    ArrayList<Story> list6;
    int a;
    int flag=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        queue= Volley.newRequestQueue(getActivity());
        type=getArguments().getInt("type",-1);
        Toast.makeText(getActivity(), type+"-----", Toast.LENGTH_SHORT).show();
        view6=inflater.inflate(R.layout.fragment_story, container, false);


        Bundle b = getArguments();
        a = b.getInt("type");

// scrollview内的线性布局
        storylayout = (LinearLayout)view6.findViewById(R.id.story_layout);
        //设置当前焦点，防止打开Activity出现在ListView位置问题
        storylayout.setFocusable(true);
        storylayout.setFocusableInTouchMode(true);
        storylayout.requestFocus();
        //找到控件   PullToRefreshScrollView
        storyscrollview= (PullToRefreshScrollView) view6.findViewById(R.id.story_scrollview);
        bigbangtextview= (TextView) view6.findViewById(R.id.bigbang);
        storylistview= (ListViewForScrollView) view6.findViewById(R.id.story_listview);
        //scrollview只允许上拉
        storyscrollview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        storyscrollview.setOnRefreshListener(this);
        storylistview.setOnItemClickListener(this);

        // 在创建视图的时候调用bigbangtextview的监听
        getBigbangtextview();

        adapter6=new StoryListViewAdapter(getActivity());
        storylistview.setAdapter(adapter6);
        //调用查询新闻表里的方法，一进来就查询显示5条数据
        getData6();

        return view6;
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
        getData6();
        storyscrollview.onRefreshComplete();

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        if(a == 6){
            Story info=adapter6.getItem(position);
            Toast.makeText(getActivity(), info.getTitle()+""+position, Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getActivity(), DetailBrowerActivity.class);
            i.putExtra("a",a);
            i.putExtra("info",info);
            startActivity(i);
        }

    }

    //查询故事表里的数据
    public void getData6(){
        list6=new ArrayList<>();
        BmobQuery<Story> query = new BmobQuery<Story>();
        query.setLimit(5);
        //setSKip方法可以做到跳过查询的前多少条数据来实现分页查询的功能。默认情况下Skip的值为10。
        // 忽略前10条数据（即第一页数据结果）
        query.setSkip(flag*5);
        query.findObjects(new FindListener<Story>() {
            @Override
            public void done(List<Story> object, BmobException e) {
                if (e == null) {
//                    Toast.makeText(MainActivity.this, "查询成功：共" + object.size() + "条数据。", Toast.LENGTH_SHORT).show();
                    for (Story story: object) {
                        //获得New里的信息
                        String title=story.getTitle();
                        String content=story.getContent();
                        BmobDate date=story.getDate();
                        BmobFile image=story.getImage();
                        //获得数据的objectId信息
                        story.getObjectId();
                        //获得createdAt数据创建时间（注意是：createdAt，不是createAt）
                        story.getCreatedAt();
                        //把所有的信息放到list集合里
                        story.setTitle(title);
                        story.setContent(content);
                        story.setDate(date);
                        story.setImage(image);
                        list6.add(story);
                    }
                    //通过adapter是配到列表上
                    if(a == 6){
                        Toast.makeText(getActivity(), list6.size()+"------------", Toast.LENGTH_SHORT).show();
                        adapter6.addDataStory(list6);
                        adapter6.notifyDataSetChanged();
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

