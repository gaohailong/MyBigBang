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
import com.example.asus.adapter.AnnouncementListViewAdapter;
import com.example.asus.eneity.Announcement;
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
public class AnnouncementFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2,AdapterView.OnItemClickListener{


    public AnnouncementFragment() {
        // Required empty public constructor
    }

    RequestQueue queue;
    int type;
    ListViewForScrollView announcementlistview;
    PullToRefreshScrollView announcementscrollview;
    AnnouncementListViewAdapter adapter2;
    View view2;
    TextView bigbangtextview;
    LinearLayout announcementlayout;
    ArrayList<Announcement> list2;
    int a;
    int flag=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        queue= Volley.newRequestQueue(getActivity());
        type=getArguments().getInt("type",-1);
        Toast.makeText(getActivity(), type+"-----", Toast.LENGTH_SHORT).show();
        view2=inflater.inflate(R.layout.fragment_announcement, container, false);


        Bundle b = getArguments();
        a = b.getInt("type");
        // scrollview内的线性布局
        announcementlayout = (LinearLayout)view2.findViewById(R.id.announcement_layout);
        //设置当前焦点，防止打开Activity出现在ListView位置问题
        announcementlayout.setFocusable(true);
        announcementlayout.setFocusableInTouchMode(true);
        announcementlayout.requestFocus();
        //找到控件   PullToRefreshScrollView
        announcementscrollview= (PullToRefreshScrollView) view2.findViewById(R.id.announcement_scrollview);
        bigbangtextview= (TextView) view2.findViewById(R.id.bigbang);
        announcementlistview= (ListViewForScrollView) view2.findViewById(R.id.announcement_listview);
        //scrollview只允许上拉
        announcementscrollview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        announcementscrollview.setOnRefreshListener(this);
        announcementlistview.setOnItemClickListener(this);

        // 在创建视图的时候调用bigbangtextview的监听
        getBigbangtextview();
        adapter2=new AnnouncementListViewAdapter(getActivity());
        announcementlistview.setAdapter(adapter2);

        getData2();

        return view2;
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
        getData2();
        announcementscrollview.onRefreshComplete();

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

       if (a == 2) {
            Announcement info = adapter2.getItem(position);
            Toast.makeText(getActivity(), info.getTitle() + "" + position, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity(), DetailBrowerActivity.class);
            i.putExtra("a", a);
            i.putExtra("info", info);
            startActivity(i);
        }
    }



    //查询公告表里的数据
    public void getData2(){
        list2=new ArrayList<>();
        BmobQuery<Announcement> query = new BmobQuery<Announcement>();
        query.setLimit(5);
        //setSKip方法可以做到跳过查询的前多少条数据来实现分页查询的功能。默认情况下Skip的值为10。
        // 忽略前10条数据（即第一页数据结果）
        query.setSkip(flag*5);
        query.findObjects(new FindListener<Announcement>() {
            @Override
            public void done(List<Announcement> object, BmobException e) {
                if (e == null) {
//                    Toast.makeText(MainActivity.this, "查询成功：共" + object.size() + "条数据。", Toast.LENGTH_SHORT).show();
                    for (Announcement announcement: object) {
                        //获得New里的信息
                        String title=announcement.getTitle();
                        String content=announcement.getContent();
                        BmobDate date=announcement.getDate();
                        BmobFile image=announcement.getImage();
                        //获得数据的objectId信息
                        announcement.getObjectId();
                        //获得createdAt数据创建时间（注意是：createdAt，不是createAt）
                        announcement.getCreatedAt();
                        //把所有的信息放到list集合里
                        announcement.setTitle(title);
                        announcement.setContent(content);
                        announcement.setDate(date);
                        announcement.setImage(image);
                        list2.add(announcement);
                    }
                    //通过adapter是配到列表上
                    if(a == 2){
                        Toast.makeText(getActivity(), list2.size()+"------------", Toast.LENGTH_SHORT).show();
                        adapter2.addDataAnnouncement(list2);
                        adapter2.notifyDataSetChanged();
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
