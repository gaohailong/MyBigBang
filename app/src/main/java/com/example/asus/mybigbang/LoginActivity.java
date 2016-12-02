package com.example.asus.mybigbang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.base.BaseActivity;
import com.example.asus.config.UrlConfig;
import com.example.asus.eneity._User;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends BaseActivity {

    EditText name;
    EditText pwd;
    Button login_btn;
    Button register_btn;
    ImageView login_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        name= (EditText) findViewById(R.id.login_name);
        pwd= (EditText) findViewById(R.id.login_pwd);
        login_btn= (Button) findViewById(R.id.login_btn);
        register_btn= (Button) findViewById(R.id.register_btn);
        login_back= (ImageView) findViewById(R.id.login_back);

    }

    @Override
    public void Listener() {
        login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1=name.getText().toString().trim();
                String pwd1=pwd.getText().toString().trim();
                if ((!name1.equals(""))&&(!pwd1.equals(""))){
                    login(name1,pwd1);
                }else {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void login(final String name3, final String pwd3){
        _User user=new _User();
        user.setUsername(name3);
        user.setPassword(pwd3);
        user.setUserPwd(pwd3);
        user.signUp(new SaveListener<Object>() {
            @Override
            public void done(Object o, BmobException e) {
                if (e==null){
                    save(name3,pwd3);
                    Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    /**
     * 保存用户名和密码到本地
     * @param name
     * @param pwd
     */
    public void save(String name,String pwd){
        SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor e=sp.edit();
        e.putString("name",name);
        e.putString("pwd",pwd);
        e.putBoolean("islogin",true);
        e.commit();
    }


    @Override
    public void loadData() {

    }
}
