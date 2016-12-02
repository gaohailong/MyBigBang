package com.example.asus.mybigbang;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.asus.base.BaseActivity;
import com.example.asus.eneity.Announcement;
import com.example.asus.eneity.News;
import com.example.asus.eneity.Quality;
import com.example.asus.eneity.Story;
import com.example.asus.eneity.Travel;
import com.nostra13.universalimageloader.core.ImageLoader;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class DetailBrowerActivity extends BaseActivity implements View.OnClickListener{

    ImageView newsbrower_back;
    TextView browertext;
    ImageView newsbrower_menu;
    TextView news_tv_title;
    TextView news_tv_date;
    TextView news_tv_content;
    ImageView news_image;
    PopupWindow pw;
    View vv;
    TextView popu_tv_collection;
    TextView popu_tv_share;

    News ninfo;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_menu_brower);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        vv= LayoutInflater.from(this).inflate(R.layout.inflater_popu_item,null);
        newsbrower_back= (ImageView) findViewById(R.id.newsbrower_back);
        browertext= (TextView) findViewById(R.id.browertext);
        newsbrower_menu= (ImageView) findViewById(R.id.newsbrower_menu);
        news_tv_title= (TextView) findViewById(R.id.news_tv_title);
        news_tv_date= (TextView) findViewById(R.id.news_tv_date);
        news_tv_content= (TextView) findViewById(R.id.news_tv_content);
        news_image= (ImageView) findViewById(R.id.news_image);
        popu_tv_collection= (TextView) vv.findViewById(R.id.popu_tv_collection);
        popu_tv_share= (TextView) vv.findViewById(R.id.popu_tv_share);

         a = getIntent().getIntExtra("a",0);
        if(a == 1){
            ninfo = (News) getIntent().getSerializableExtra("info");
            browertext.setText("新闻");
            news_tv_title.setText(ninfo.getTitle());
            news_tv_date.setText(ninfo.getDate().getDate());
            news_tv_content.setText(ninfo.getContent());
            ImageLoader.getInstance().displayImage(String.valueOf(ninfo.getImage().getFileUrl()),news_image);
        }else if(a==2){
            Announcement info = (Announcement) getIntent().getSerializableExtra("info");
            browertext.setText("公告");
            news_tv_title.setText(info.getTitle());
            news_tv_date.setText(info.getDate().getDate());
            news_tv_content.setText(info.getContent());
            ImageLoader.getInstance().displayImage(String.valueOf(info.getImage().getFileUrl()),news_image);
        }else if(a==3){
            Quality info = (Quality) getIntent().getSerializableExtra("info");
            browertext.setText("精品");
            news_tv_title.setText(info.getTitle());
            news_tv_date.setText(info.getDate().getDate());
            news_tv_content.setText(info.getContent());
            ImageLoader.getInstance().displayImage(String.valueOf(info.getImage().getFileUrl()),news_image);
        }else if (a==6){
            Story info= (Story) getIntent().getSerializableExtra("info");
            browertext.setText("故事");
            news_tv_title.setText(info.getTitle());
            news_tv_date.setText(info.getDate().getDate());
            news_tv_content.setText(info.getContent());
            ImageLoader.getInstance().displayImage(String.valueOf(info.getImage().getFileUrl()),news_image);
        }else if(a==7){
            Travel info= (Travel) getIntent().getSerializableExtra("info");
            browertext.setText("行程");
            news_tv_title.setText(info.getContent());
            news_tv_date.setText(info.getDate().getDate());
            ImageLoader.getInstance().displayImage(info.getImage().getFileUrl(),news_image);
        }


    }

    @Override
    public void Listener() {
        newsbrower_back.setOnClickListener(this);
        newsbrower_menu.setOnClickListener(this);
        popu_tv_collection.setOnClickListener(this);
        popu_tv_share.setOnClickListener(this);
    }

    @Override
    public void loadData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newsbrower_back:
                finish();
                break;
            case R.id.newsbrower_menu:
//                View vv= LayoutInflater.from(this).inflate(R.layout.inflater_popu_item,null);//一定不能忘了这句话
                pw=new PopupWindow(vv,LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT,true);
                pw.setBackgroundDrawable(getResources().getDrawable(R.mipmap.popup));
                pw.showAsDropDown(v);
                break;
            case R.id.popu_tv_collection:
                Toast.makeText(DetailBrowerActivity.this, "收藏", Toast.LENGTH_SHORT).show();

                if(a == 1){
                   //收藏表的实体类  获取对象
//                    Person p2 = new Person();
//                    p2.setName(ninfo.name);
//                    p2.setAddress("北京海淀");
//                    p2.save(new SaveListener<String>() {
//                        @Override
//                        public void done(String objectId,BmobException e) {
//                            if(e==null){
//                                toast("添加数据成功，返回objectId为："+objectId);
//                            }else{
//                                toast("创建数据失败：" + e.getMessage());
//                            }
//                        }
//                    });


                }else if(a == 2){

                }

                pw.dismiss();
                break;
            case R.id.popu_tv_share:
                Toast.makeText(DetailBrowerActivity.this, "分享", Toast.LENGTH_SHORT).show();
                pw.dismiss();
                break;

        }

    }

}
