package com.example.asus.eneity;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2016/11/28.
 */
public class Photo {
    private BmobFile image;
    private String title;
    public BmobFile getImage() {
        return image;
    }

    public void setImage(BmobFile image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
