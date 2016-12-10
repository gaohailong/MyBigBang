package com.example.asus.mybigbang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
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
import com.example.asus.eneity.Collection;
import com.example.asus.eneity.News;
import com.example.asus.eneity.Photo;
import com.example.asus.eneity.Quality;
import com.example.asus.eneity.Story;
import com.example.asus.eneity.Travel;
import com.example.asus.eneity.Vote;
import com.example.asus.eneity._User;
import com.nostra13.universalimageloader.core.ImageLoader;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

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
    Announcement ainfo;
    Quality qinfo;
    Vote vinfo;
    Story sinfo;
    Travel tinfo;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_menu_brower);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        //初始化ShareSDK
        ShareSDK.initSDK(DetailBrowerActivity.this,"19a2ac8f09f98");
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

        //接收a的值，判断点击的是新闻，公告，精品，相册、投票，故事还是行程页的Item
        a = getIntent().getIntExtra("a",0);
        if(a == 1){
            ninfo = (News) getIntent().getSerializableExtra("info");
            browertext.setText("新闻");
            news_tv_title.setText(ninfo.getTitle());
            news_tv_date.setText(ninfo.getDate().getDate());
            news_tv_content.setText(ninfo.getContent());
            ImageLoader.getInstance().displayImage(String.valueOf(ninfo.getImage().getFileUrl()),news_image);
        }else if(a==2){
            ainfo = (Announcement) getIntent().getSerializableExtra("info");
            browertext.setText("公告");
            news_tv_title.setText(ainfo.getTitle());
            news_tv_date.setText(ainfo.getDate().getDate());
            news_tv_content.setText(ainfo.getContent());
            ImageLoader.getInstance().displayImage(String.valueOf(ainfo.getImage().getFileUrl()),news_image);
        }else if(a==3){
            qinfo = (Quality) getIntent().getSerializableExtra("info");
            browertext.setText("精品");
            news_tv_title.setText(qinfo.getTitle());
            news_tv_date.setText(qinfo.getDate().getDate());
            news_tv_content.setText(qinfo.getContent());
            ImageLoader.getInstance().displayImage(String.valueOf(qinfo.getImage().getFileUrl()),news_image);
        }else if (a==5){
            vinfo= (Vote) getIntent().getSerializableExtra("info");
            browertext.setText("投票教程");
            news_tv_title.setText(vinfo.getTitle());
            news_tv_date.setText(vinfo.getDate().getDate());
            news_tv_content.setText(vinfo.getContent());
            ImageLoader.getInstance().displayImage(vinfo.getImage().getFileUrl(),news_image);
        } else if (a==6){
            sinfo= (Story) getIntent().getSerializableExtra("info");
            browertext.setText("故事");
            news_tv_title.setText(sinfo.getTitle());
            news_tv_date.setText(sinfo.getDate().getDate());
            news_tv_content.setText(sinfo.getContent());
            ImageLoader.getInstance().displayImage(String.valueOf(sinfo.getImage().getFileUrl()),news_image);
        }else if(a==7){
            tinfo= (Travel) getIntent().getSerializableExtra("info");
            browertext.setText("行程");
            news_tv_title.setText(tinfo.getContent());
            news_tv_date.setText(tinfo.getDate().getDate());
            ImageLoader.getInstance().displayImage(tinfo.getImage().getFileUrl(),news_image);
        }else if(a == 8){
            Collection collection = (Collection) getIntent().getSerializableExtra("info");
            browertext.setText("收藏");
            news_tv_title.setText(collection.getTitle());
            news_tv_date.setText(collection.getDate().getDate());
            news_tv_content.setText(collection.getContent());
            ImageLoader.getInstance().displayImage(collection.getImage().getFileUrl(),news_image);
            newsbrower_menu.setVisibility(View.GONE);
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
                Intent i=new Intent();
                if (isLogin()){//可以收藏
                    if(a == 1){
                        //收藏表的实体类  获取对象
                        SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
                        String id= sp.getString("objectid",null);
                        _User user=new _User();
                        user.setObjectId(id);
                        Collection collection=new Collection();
                        collection.setTitle(ninfo.getTitle());
                        collection.setContent(ninfo.getContent());
                        collection.setDate(ninfo.getDate());
                        collection.setImage(ninfo.getImage());
                        collection.setId(user);
                            collection.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e==null){
                                    Toast.makeText(DetailBrowerActivity.this, "收藏成功！", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(DetailBrowerActivity.this, "收藏失败！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else if(a == 2){
                        SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
                        String id= sp.getString("objectid",null);
                        _User user=new _User();
                        user.setObjectId(id);
                        Collection collection=new Collection();
                        collection.setTitle(ainfo.getTitle());
                        collection.setContent(ainfo.getContent());
                        collection.setDate(ainfo.getDate());
                        collection.setImage(ainfo.getImage());
                        collection.setId(user);
                        collection.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e==null){
                                    Toast.makeText(DetailBrowerActivity.this, "收藏成功！", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(DetailBrowerActivity.this, "收藏失败！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else if (a==3){
                        SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
                        String id= sp.getString("objectid",null);
                        _User user=new _User();
                        user.setObjectId(id);
                        Collection collection=new Collection();
                        collection.setTitle(qinfo.getTitle());
                        collection.setContent(qinfo.getContent());
                        collection.setDate(qinfo.getDate());
                        collection.setImage(qinfo.getImage());
                        collection.setId(user);
                        collection.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e==null){
                                    Toast.makeText(DetailBrowerActivity.this, "收藏成功！", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(DetailBrowerActivity.this, "收藏失败！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else if (a==5){
                        SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
                        String id= sp.getString("objectid",null);
                        _User user=new _User();
                        user.setObjectId(id);
                        Collection collection=new Collection();
                        collection.setTitle(vinfo.getTitle());
                        collection.setContent(vinfo.getContent());
                        collection.setDate(vinfo.getDate());
                        collection.setImage(vinfo.getImage());
                        collection.setId(user);
                        collection.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e==null){
                                    Toast.makeText(DetailBrowerActivity.this, "收藏成功！", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(DetailBrowerActivity.this, "收藏失败！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else if (a==6){
                        SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
                        String id= sp.getString("objectid",null);
                        _User user=new _User();
                        user.setObjectId(id);
                        Collection collection=new Collection();
                        collection.setTitle(sinfo.getTitle());
                        collection.setContent(sinfo.getContent());
                        collection.setDate(sinfo.getDate());
                        collection.setImage(sinfo.getImage());
                        collection.setId(user);
                        collection.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e==null){
                                    Toast.makeText(DetailBrowerActivity.this, "收藏成功！", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(DetailBrowerActivity.this, "收藏失败！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else if (a==7){
                        SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
                        String id= sp.getString("objectid",null);
                        _User user=new _User();
                        user.setObjectId(id);
                        Collection collection=new Collection();
                        collection.setContent(tinfo.getContent());
                        collection.setDate(tinfo.getDate());
                        collection.setImage(tinfo.getImage());
                        collection.setId(user);
                        collection.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e==null){
                                    Toast.makeText(DetailBrowerActivity.this, "收藏成功！", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(DetailBrowerActivity.this, "收藏失败！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                }else {//跳转到登录页面
                    i.setClass(this,LoginActivity.class);
                    startActivity(i);
                }

                pw.dismiss();
                break;
            case R.id.popu_tv_share:
                Intent ii=new Intent();
                if (isLogin()){
                    showShare();
                }else {
                    ii.setClass(this,LoginActivity.class);
                    startActivity(ii);
                }

                pw.dismiss();
                break;

        }

    }



    public boolean isLogin(){
        SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
        boolean islogin=sp.getBoolean("islogin",false);
        return islogin;
    }


    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
    //关闭sso授权
        oks.disableSSOWhenAuthorize();
    // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        if(a == 1){
            oks.setTitle(ninfo.getTitle());
        }

    // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
    // text是分享文本，所有平台都需要这个字段
        oks.setText("*好友邀你来VIP饭团逛一逛*");
    // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
    //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
    // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
    // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
    // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
    // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
    // 启动分享GUI
        oks.show(this);

    }



}
