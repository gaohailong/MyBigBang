package com.example.asus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.asus.adapter.RecycleViewAdapter;
import com.example.asus.mybigbang.R;

/**
 *
 * create an instance of this fragment.
 */
public class PhotoFragment extends Fragment implements AdapterView.OnItemClickListener{

    public PhotoFragment() {
        // Required empty public constructor
    }

    View view;
    RecyclerView recyclerView;
    RecycleViewAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view=inflater.inflate(R.layout.fragment_photo, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview);
        adapter=new RecycleViewAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        GridLayoutManager lin = new GridLayoutManager(getActivity(),2);
        lin.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lin);

        adapter.notifyDataSetChanged();

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //拖拽
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                //RecyclerView滑动删除,侧滑回调,滑动删除数据处理
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                adapter.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            }
        });
        helper.attachToRecyclerView(recyclerView);


        return view;

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

    }
}