package com.example.asus.eneity;

import android.graphics.Point;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2016/12/3.
 */
public class Collection extends BmobObject{
    private String title;
    private String content;
    private BmobFile image;
    private BmobDate date;
    public _User Id;

    public _User getId() {
        return Id;
    }

    public void setId(_User id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
