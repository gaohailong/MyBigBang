package com.example.asus.fragment;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.asus.adapter.RecycleViewAdapter;
import com.example.asus.eneity.News;
import com.example.asus.eneity.Photo;
import com.example.asus.eneity.Travel;
import com.example.asus.mybigbang.DetailBrowerActivity;
import com.example.asus.mybigbang.PhotoBrowerActivity;
import com.example.asus.mybigbang.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 *
 * create an instance of this fragment.
 */
public class PhotoFragment extends Fragment{

    public PhotoFragment() {
        // Required empty public constructor
    }

    View view;
    RecyclerView recyclerView;
    RecycleViewAdapter adapter;
    ArrayList<Photo> list4;
    int a;
    int lastItem;
    int[] lastVisibleItem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view=inflater.inflate(R.layout.fragment_photo, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview);

        adapter=new RecycleViewAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        //设置recyclerview的样式
        final StaggeredGridLayoutManager glm = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        glm.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(glm);

        adapter.notifyDataSetChanged();
        Bundle b = getArguments();
        a = b.getInt("type");

        getData4();


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE && lastItem+1==adapter.getItemCount()){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    getData4();
                                }
                            });
                        }
                    }).start();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem=glm.findLastCompletelyVisibleItemPositions(null);
                lastItem=Math.max(lastVisibleItem[0],lastVisibleItem[1]);
            }
        });

        adapter.setOnItemClickListener(new RecycleViewAdapter.MyItemClickListener() {
            @Override
            public void OnItemClick(View itemview,int position) {
                  Intent intent=new Intent(getActivity(),PhotoBrowerActivity.class);
                  Photo photo=list4.get(position);
                  intent.putExtra("info",photo);
                  startActivity(intent);
            }
        });

        return view;

    }






    //查询相册表里的数据
    public ArrayList<Photo> getData4(){
        list4=new ArrayList<>();
        BmobQuery<Photo> query = new BmobQuery<Photo>();
        query.findObjects(new FindListener<Photo>() {
            @Override
            public void done(List<Photo> object, BmobException e) {
                if (e==null){
                    for (Photo photo: object) {
                        //获得New里的信息
                        String title=photo.getTitle();
                        BmobFile image=photo.getImage();
                        //把所有的信息放到list集合里
                        photo.setTitle(title);
                        photo.setImage(image);
                        list4.add(photo);
                    }
                    adapter.add(object);
                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(getActivity(), "查询失败！"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("TAG",e.getErrorCode()+","+e.getMessage());
                }
            }
        });
    return list4;
    }

}