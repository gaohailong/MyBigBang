package com.example.asus.mybigbang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.base.BaseActivity;
import com.example.asus.eneity.Photo;
import com.nostra13.universalimageloader.core.ImageLoader;

public class PhotoBrowerActivity extends BaseActivity {

    TextView photo_title;
    ImageView photo_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_photo_brower);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        photo_title= (TextView) findViewById(R.id.photo_title);
        photo_image= (ImageView) findViewById(R.id.photo_image);

        Photo pinfo= (Photo) getIntent().getSerializableExtra("info");
        photo_title.setText(pinfo.getTitle());
        ImageLoader.getInstance().displayImage(String.valueOf(pinfo.getImage().getFileUrl()),photo_image);
    }

    @Override
    public void Listener() {

    }

    @Override
    public void loadData() {

    }

}
