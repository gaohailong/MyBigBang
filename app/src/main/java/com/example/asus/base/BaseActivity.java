package com.example.asus.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Administrator on 2016/11/14.
 */
public abstract class BaseActivity extends FragmentActivity {

    public abstract void init();
    public abstract void Listener();
    public abstract void loadData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        Listener();
        loadData();
    }
}
