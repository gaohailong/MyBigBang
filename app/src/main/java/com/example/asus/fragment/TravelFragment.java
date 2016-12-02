package com.example.asus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.asus.adapter.TravelListViewAdapter;
import com.example.asus.eneity.Story;
import com.example.asus.eneity.Travel;
import com.example.asus.mybigbang.DetailBrowerActivity;
import com.example.asus.mybigbang.R;
import com.example.asus.view.ListViewForScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.joybar.librarycalendar.data.CalendarDate;
import com.joybar.librarycalendar.fragment.CalendarViewFragment;
import com.joybar.librarycalendar.fragment.CalendarViewPagerFragment;

import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class TravelFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2,
        AdapterView.OnItemClickListener {


    public TravelFragment() {
        // Required empty public constructor
    }

    View view;
    ListViewForScrollView listview;
    RequestQueue queue;
    int type;
    PullToRefreshScrollView scrollview;
    TravelListViewAdapter adapter7;
    LinearLayout layout;
    ArrayList<Story> list7;
    int a;
    int flag=0;
    CalendarView calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        queue= Volley.newRequestQueue(getActivity());
        type=getArguments().getInt("type",-1);
        Toast.makeText(getActivity(), type+"-----", Toast.LENGTH_SHORT).show();
        view=inflater.inflate(R.layout.fragment_travel, container, false);
        listview= (ListViewForScrollView) view.findViewById(R.id.travel_listview);
        calendar = (CalendarView) view.findViewById(R.id.calendar);

        Bundle b = getArguments();
        a = b.getInt("type");
        // scrollview内的线性布局
        layout = (LinearLayout)view.findViewById(R.id.travellayout);
        //设置当前焦点，防止打开Activity出现在ListView位置问题
        layout.setFocusable(true);
        layout.setFocusableInTouchMode(true);
        layout.requestFocus();
        //找到控件   PullToRefreshScrollView
        scrollview= (PullToRefreshScrollView) view.findViewById(R.id.refreshscrollview);
        listview= (ListViewForScrollView) view.findViewById(R.id.travel_listview);

        //scrollview只允许上拉
        scrollview.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        scrollview.setOnRefreshListener(this);
        listview.setOnItemClickListener(this);

        adapter7=new TravelListViewAdapter(getActivity());
        listview.setAdapter(adapter7);

        getData7();
        return view;
    }



    //scrollview的下拉刷新
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
    }

    //scrollview的上拉加载
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

        flag++;
        getData7();
        scrollview.onRefreshComplete();

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        if (a == 7){
            Travel info=adapter7.getItem(position);
            Toast.makeText(getActivity(), info.getContent()+""+position, Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getActivity(), DetailBrowerActivity.class);
            i.putExtra("a",a);
            i.putExtra("info",info);
            startActivity(i);
        }
    }


    //查询精品表里的数据
    public void getData7(){
        list7=new ArrayList<>();
        BmobQuery<Travel> query = new BmobQuery<Travel>();
        query.setLimit(5);
        //setSKip方法可以做到跳过查询的前多少条数据来实现分页查询的功能。默认情况下Skip的值为10。
        // 忽略前10条数据（即第一页数据结果）
        query.setSkip(flag*5);
        query.findObjects(new FindListener<Travel>() {
            @Override
            public void done(List<Travel> object, BmobException e) {
                if (e==null){
                    //通过adapter是配到列表上
                    if(a == 7){
                        Toast.makeText(getActivity(), list7.size()+"------------", Toast.LENGTH_SHORT).show();
                        adapter7.addDataTravel(object);
                        adapter7.notifyDataSetChanged();
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





