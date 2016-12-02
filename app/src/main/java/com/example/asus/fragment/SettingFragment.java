package com.example.asus.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.mybigbang.AboutActivity;
import com.example.asus.mybigbang.LoginActivity;
import com.example.asus.mybigbang.R;
import com.example.asus.mybigbang.RegisterActivity;

/**
 * 设置界面的Fragment
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {


    View view ;
    TextView setting_login;
    TextView setting_register;
    TextView setting_about;
    TextView setting_collection;


    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_setting, container, false);
        setting_login= (TextView) view.findViewById(R.id.setting_login);
        setting_register= (TextView) view.findViewById(R.id.setting_register);
        setting_about= (TextView) view.findViewById(R.id.setting_about);
        setting_collection= (TextView) view.findViewById(R.id.setting_collection);

        setting_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        setting_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        setting_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });

        setting_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

}
