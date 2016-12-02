package com.example.asus.eneity;

import java.io.File;
import java.util.Date;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2016/11/27.
 */
public class News extends BmobObject {
    private String title;
    private String content;
    private BmobFile image;
    private BmobDate date;
    private Integer like;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public BmobFile getImage() {
        return image;
    }

    public void setImage(BmobFile image) {
        this.image = image;
    }

    public BmobDate getDate() {
        return date;
    }

    public void setDate(BmobDate date) {
        this.date = date;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Number getLike() {
        return like;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }




}
