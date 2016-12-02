package com.example.asus.mybigbang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asus.adapter.NewsListViewAdapter;
import com.example.asus.base.BaseActivity;

public class CollectionActivity extends BaseActivity {

    ListView listView;
    NewsListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_collection);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        listView= (ListView)findViewById(R.id.collection_listview);
        adapter=new NewsListViewAdapter(this);
        listView.setAdapter(adapter);

    }

    @Override
    public void Listener() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positon, long l) {
                Intent intent = new Intent(CollectionActivity.this, DetailBrowerActivity.class);
                intent.putExtra("info",adapter.getItem(positon));
                startActivity(intent);
            }
        });


    }

    @Override
    public void loadData() {

    }
}
