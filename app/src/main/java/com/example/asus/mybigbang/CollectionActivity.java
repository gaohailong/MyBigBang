package com.example.asus.mybigbang;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.adapter.CollectionListViewAdapter;
import com.example.asus.base.BaseActivity;
import com.example.asus.eneity.Collection;


import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class CollectionActivity extends BaseActivity {

    ImageView collection_back;
    ListView listView;
    CollectionListViewAdapter adapter;
    ArrayList<Collection> collectionlist;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_collection);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        collection_back= (ImageView) findViewById(R.id.collection_back);
        listView= (ListView)findViewById(R.id.collection_listview);
        adapter=new CollectionListViewAdapter(this);
        listView.setAdapter(adapter);

    }

    @Override
    public void Listener() {
        collection_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positon, long l) {
                Intent intent = new Intent(CollectionActivity.this, DetailBrowerActivity.class);
                intent.putExtra("a",8);
                intent.putExtra("info",adapter.getItem(positon));
                startActivity(intent);


            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Collection c = adapter.getItem(i);
                AlertDialog.Builder dialog=new AlertDialog.Builder(CollectionActivity.this);
                dialog.setTitle("删除提示");
                dialog.setMessage("是否删除此条收藏？");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        c.delete(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e==null){
                                    loadData();
                                }else {

                                }
                            }
                        });
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("取消",null);
                dialog.create();
                dialog.show();



                return true;
            }
        });


    }

    @Override
    public void loadData() {
        collectionlist=new ArrayList<>();
        BmobQuery<Collection> query = new BmobQuery<Collection>();
        SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
        String id= sp.getString("objectid",null);
        query.addWhereEqualTo("Id",id);
        query.findObjects(new FindListener<Collection>() {
            @Override
            public void done(List<Collection> object, BmobException e) {
                if (e==null){
                    //通过adapter是配到列表上
                    adapter.addDataCollection(object);
                    adapter.notifyDataSetChanged();
                    }else {
                    Toast.makeText(CollectionActivity.this, "查询失败！"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("TAG",e.getErrorCode()+","+e.getMessage());
                }

                }
        });

    }
}
