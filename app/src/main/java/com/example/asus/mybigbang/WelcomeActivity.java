package com.example.asus.mybigbang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.asus.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    ImageView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_welcome);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void init() {
        welcome= (ImageView) findViewById(R.id.welcome_image);
    }

    @Override
    public void Listener() {

    }

    @Override
    public void loadData() {
        //android中的多线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        }).start();

    }

}
