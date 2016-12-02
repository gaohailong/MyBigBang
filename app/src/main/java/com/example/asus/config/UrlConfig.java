package com.example.asus.config;

/**
 * Created by Administrator on 2016/11/15.
 */
public class UrlConfig {

    public static final String URL="http://123.206.14.217:8080/news";

    public static final String DONEXT_URL=URL+"/DoNewsNextList?size=5";

    public static final String DOPRE_URL=URL+"/DoNewsNextList?size=5";

    public static final String COMMENTCOUNT_URL=URL+"/DoCommentCount?nid=";

    public static final String LOGIN_URL=URL+"/ClientDoUserLogin?";

    public static final String REGISTER_URL=URL+"/ClientDoUserRegister?";

    public static final String COMMENTADD_URL=URL+"/DoCommentAdd?";

    public static final String COMMENTNEXT_URL=URL+"/DoCommentNextList?size=10";

    public static final String COMMENTPRE_URL=URL+"/DoCommentPreList?size=10";
}
