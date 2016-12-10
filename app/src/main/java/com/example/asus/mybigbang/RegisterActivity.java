package com.example.asus.mybigbang;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asus.base.BaseActivity;
import com.example.asus.eneity._User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivity {

    EditText register_name;
    EditText register_pwd;
    EditText register_pwdagain;

    Button register_btn1;
    Button cancel_btn;
    ImageView register_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {
        register_name= (EditText) findViewById(R.id.register_name);
        register_pwd= (EditText) findViewById(R.id.register_pwd);
        register_pwdagain= (EditText) findViewById(R.id.register_pwdagain);
        register_btn1= (Button) findViewById(R.id.register_btn1);
        cancel_btn= (Button) findViewById(R.id.cancel_btn);

        register_back= (ImageView) findViewById(R.id.register_back);

    }

    @Override
    public void Listener() {
        register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_name.setText(null);
                register_pwd.setText(null);
                register_pwdagain.setText(null);
            }
        });

        register_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name2=register_name.getText().toString().trim();
                String pwd2=register_pwd.getText().toString().trim();
                String pwdagain2=register_pwdagain.getText().toString().trim();
                if ((!name2.equals(""))&&(!pwd2.equals(""))&&(!pwdagain2.equals(""))){
                    if (pwd2.equals(pwdagain2)){
                        register(name2,pwd2);
                    }else {
                        Toast.makeText(RegisterActivity.this, "两次输入密码不一致！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void register(final String name, final String pwd){
        _User user=new _User();
        user.setUsername(name);
        user.setPassword(pwd);
        user.setUserPwd(pwd);
        user.signUp(new SaveListener<Object>() {
            @Override
            public void done(Object o, BmobException e) {
                if (e==null){
                    saveregister(name,pwd);
                    Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * 保存注册后的用户名和密码
     * @param name
     * @param pwd
     */
    public void saveregister(String name,String pwd){
        SharedPreferences sp=getSharedPreferences("register",MODE_PRIVATE);
        SharedPreferences.Editor e=sp.edit();
        e.putString("name1",name);
        e.putString("pwd1",pwd);
        e.putBoolean("isregister",true);
        e.commit();
    }



    @Override
    public void loadData() {

    }


}

