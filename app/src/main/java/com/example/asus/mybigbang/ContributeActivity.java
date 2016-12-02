package com.example.asus.mybigbang;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.base.BaseActivity;
import com.example.asus.eneity.Announcement;
import com.example.asus.eneity.News;
import com.example.asus.eneity.Photo;
import com.example.asus.eneity.Quality;
import com.example.asus.eneity.Story;
import com.example.asus.eneity.Travel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class ContributeActivity extends BaseActivity implements View.OnClickListener{

    ImageView back_image;
    Button issue;
    EditText issue_title;
    ImageView upload_image;
    EditText content;
    TextView whereissue;
    View vi;
    PopupWindow pw;
    TextView popup_tv_news;
    TextView popup_tv_announcement;
    TextView popup_tv_quality;
    TextView popup_tv_photo;
    TextView popup_tv_vote;
    TextView popup_tv_story;
    TextView popup_tv_travel;
    public final int IMAGE=1;
    File f;
    int flag=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contribute);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        vi= LayoutInflater.from(this).inflate(R.layout.inflater_popup_item,null);
        Bmob.initialize(this,"441eea9b8abf7a3bcfc47a8f8fa5dad0");
        back_image= (ImageView) findViewById(R.id.back_image);
        issue= (Button) findViewById(R.id.issue);
        issue_title= (EditText) findViewById(R.id.issue_title);
        upload_image= (ImageView) findViewById(R.id.upload_image);
        content= (EditText) findViewById(R.id.content);
        whereissue= (TextView) findViewById(R.id.whereissue);

        popup_tv_news= (TextView) vi.findViewById(R.id.popup_tv_news);
        popup_tv_announcement= (TextView) vi.findViewById(R.id.popup_tv_announcement);
        popup_tv_quality= (TextView) vi.findViewById(R.id.popup_tv_quality);
        popup_tv_photo= (TextView) vi.findViewById(R.id.popup_tv_photo);
        popup_tv_vote= (TextView) vi.findViewById(R.id.popup_tv_vote);
        popup_tv_story= (TextView) vi.findViewById(R.id.popup_tv_story);
        popup_tv_travel= (TextView) vi.findViewById(R.id.popup_tv_travel);

    }

    @Override
    public void Listener() {
       back_image.setOnClickListener(this);
        //发布按钮的监听
        issue.setOnClickListener(this);
        //上传图片的监听
        upload_image.setOnClickListener(this);
        //选择发布频道的监听
        whereissue.setOnClickListener(this);
        popup_tv_news.setOnClickListener(this);
        popup_tv_announcement.setOnClickListener(this);
        popup_tv_quality.setOnClickListener(this);
        popup_tv_photo.setOnClickListener(this);
        popup_tv_vote.setOnClickListener(this);
        popup_tv_story.setOnClickListener(this);
        popup_tv_travel.setOnClickListener(this);

    }

    @Override
    public void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_image:
                finish();
                break;
            case R.id.upload_image:
                //隐式意图跳转到本地相册
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,IMAGE);
                break;
            case R.id.issue:
                //发布按钮的监听事件
                final String title=issue_title.getText().toString().trim();
                final String issuecontent=content.getText().toString().trim();
                final String date=getNowDate();
                final BmobFile bf=new BmobFile(f);
                bf.upload(new UploadFileListener() {
                    @Override
                    public void done(BmobException e) {
                        if(flag==1){
                            News inew=new News();
                            inew.setImage(bf);
                            inew.setTitle(title);
                            inew.setContent(issuecontent);

                            inew.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e==null){
                                        Toast.makeText(ContributeActivity.this, "上传成功！", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(ContributeActivity.this, "上传失败！", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        }else if (flag==2){
                            Announcement announcement=new Announcement();
                            announcement.setImage(bf);
                            announcement.setTitle(title);
                            announcement.setContent(issuecontent);
                            announcement.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e==null){
                                        Toast.makeText(ContributeActivity.this, "上传成功！", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(ContributeActivity.this, "上传失败！", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        }else if (flag==3){
                            Quality quality=new Quality();
                            quality.setImage(bf);
                            quality.setTitle(title);
                            quality.setContent(issuecontent);
                            quality.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e==null){
                                        Toast.makeText(ContributeActivity.this, "上传成功！", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(ContributeActivity.this, "上传失败！", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        }else if (flag==4){
                            Photo photo=new Photo();
                            photo.setImage(bf);
                            photo.setTitle(title);


                        }else if (flag==5){

                        }else if (flag==6){
                            Story story=new Story();
                            story.setImage(bf);
                            story.setTitle(title);
                            story.setContent(issuecontent);
                            story.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e==null){
                                        Toast.makeText(ContributeActivity.this, "上传成功！", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(ContributeActivity.this, "上传失败！", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        }else if (flag==7){
                            Travel travel=new Travel();
                            travel.setImage(bf);
                            travel.setContent(issuecontent);
                            travel.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e==null){
                                        Toast.makeText(ContributeActivity.this, "上传成功！", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(ContributeActivity.this, "上传失败！", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                        }

                    }
                });
                break;
            //popupWindow弹出要选择的频道
            case R.id.whereissue:
                pw=new PopupWindow(vi,LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT,true);
                pw.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ppop));
                pw.showAsDropDown(v);
                break;
            case R.id.popup_tv_news:
                Toast.makeText(ContributeActivity.this, "新闻", Toast.LENGTH_SHORT).show();
                whereissue.setText("新闻");
                flag=1;
                pw.dismiss();
                break;
            case R.id.popup_tv_announcement:
                Toast.makeText(ContributeActivity.this, "公告", Toast.LENGTH_SHORT).show();
                whereissue.setText("公告");
                flag=2;
                pw.dismiss();
                break;
            case R.id.popup_tv_quality:
                Toast.makeText(ContributeActivity.this, "精品", Toast.LENGTH_SHORT).show();
                whereissue.setText("精品");
                flag=3;
                pw.dismiss();
                break;
            case R.id.popup_tv_photo:
                Toast.makeText(ContributeActivity.this, "相册", Toast.LENGTH_SHORT).show();
                whereissue.setText("相册");
                flag=4;
                pw.dismiss();
            case R.id.popup_tv_vote:
                Toast.makeText(ContributeActivity.this, "投票教程", Toast.LENGTH_SHORT).show();
                whereissue.setText("投票教程");
                flag=5;
                pw.dismiss();
                break;
            case R.id.popup_tv_story:
                Toast.makeText(ContributeActivity.this, "故事", Toast.LENGTH_SHORT).show();
                whereissue.setText("故事");
                flag=6;
                pw.dismiss();
                break;
            case R.id.popup_tv_travel:
                Toast.makeText(ContributeActivity.this, "行程", Toast.LENGTH_SHORT).show();
                whereissue.setText("行程");
                flag=7;
                pw.dismiss();
                break;
        }

    }

    //回调方法,返回选中的那张照片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            f=new File(imagePath);
            Bitmap bm = BitmapFactory.decodeFile(imagePath);
            upload_image.setImageBitmap(bm);
            c.close();
        }




    }

    public String getNowDate(){
        long l = System.currentTimeMillis();
        Date d = new Date(l);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-DD hh:mm");
        String str = f.format(d);
        return str;
    }

}

