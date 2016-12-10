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
import com.example.asus.adapter.NewsListViewAdapter;
import com.example.asus.adapter.VoteListViewAdapter;
import com.example.asus.eneity.News;
import com.example.asus.eneity.Vote;
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
 * 投票教程数据界面
 * A simple {@link Fragment} subclass.
 */
public class VoteFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2,AdapterView.OnItemClickListener{


    public VoteFragment() {
        // Required empty public constructor
    }

    RequestQueue queue;
    int type;
    ListViewForScrollView votelistview;
    PullToRefreshScrollView votescrollview;
    VoteListViewAdapter adapter5;
    View view5;
    TextView bigbangtextview;
    LinearLayout votelayout;
    ArrayList<Vote> list5;
    int a;
    int flag=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        queue= Volley.newRequestQueue(getActivity());
        type=getArguments().getInt("type",-1);
        view5=inflater.inflate(R.layout.fragment_vote, container, false);

        Bundle b=getArguments();
        a=b.getInt("type");
        // scrollview内的线性布局 v
        votelayout = (LinearLayout)view5.findViewById(R.id.vote_layout);
        //设置当前焦点，防止打开Activity出现在ListView位置问题
        votelayout.setFocusable(true);
        votelayout.setFocusableInTouchMode(true);
        votelayout.requestFocus();
        //找到控件   PullToRefreshScrollView
        votescrollview= (PullToRefreshScrollView) view5.findViewById(R.id.vote_scrollview);
        bigbangtextview= (TextView) view5.findViewById(R.id.bigbang);
        votelistview= (ListViewForScrollView) view5.findViewById(R.id.vote_listview);
        //scrollview只允许上拉
        votescrollview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        votescrollview.setOnRefreshListener(this);
        votelistview.setOnItemClickListener(this);

        // 在创建视图的时候调用bigbangtextview的监听
        getBigbangtextview();

        adapter5=new VoteListViewAdapter(getActivity());
        votelistview.setAdapter(adapter5);
        //调用查询新闻表里的方法，一进来就查询显示5条数据

        getData5();

        return view5;
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
        getData5();
        votescrollview.onRefreshComplete();

    }

    //投票教程每一行的点击事件，跳至消息详情页
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Vote vote=adapter5.getItem(position);
            Toast.makeText(getActivity(), vote.getTitle()+""+position, Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getActivity(), DetailBrowerActivity.class);
            i.putExtra("a",a);
            i.putExtra("info",vote);
            startActivity(i);

    }


    //查询投票教程表里的数据
    public void getData5(){
        list5=new ArrayList<>();
        BmobQuery<Vote> query = new BmobQuery<Vote>();
        query.setLimit(3);
        //setSKip方法可以做到跳过查询的前多少条数据来实现分页查询的功能。默认情况下Skip的值为10。
        // 忽略前10条数据（即第一页数据结果）
        query.setSkip(flag*3);
        query.findObjects(new FindListener<Vote>() {
            @Override
            public void done(List<Vote> object, BmobException e) {
                if (e == null) {
                    //通过adapter是配到列表上
                        adapter5.addDataVote(object);
                        adapter5.notifyDataSetChanged();

                } else {
                    Toast.makeText(getActivity(), "查询失败！"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("TAG",e.getErrorCode()+"-+-+-+-+-+-+"+","+e.getMessage());
                }
            }
        });
    }
}
