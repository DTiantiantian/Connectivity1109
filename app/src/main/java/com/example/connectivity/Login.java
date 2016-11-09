package com.example.connectivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 注册界面
 * Created by 杨航 on 2016/10/30.
 */
public class Login extends Activity {

    public Login() {
        MyValues myValues =new MyValues();
        Validate_phone vphone = new Validate_phone();
        Validate_login vlogin = new Validate_login();
        myValues.setLogin(this);
        vphone.setLogin(this);
        vlogin.setLogin(this);
    }

    private CheckBox ask;
    private EditText e1 = null, e2 = null;
    private String username = null, password = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        e1 = (EditText) findViewById(R.id.login_username);
        e2 = (EditText) findViewById(R.id.login_password);
        ask = (CheckBox) findViewById(R.id.login_ask);
    }

    public void getinformation() {
        username = e1.getText().toString().trim();
        password = e2.getText().toString().trim();
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_nologin:
                startActivity(new Intent(this, Validate_login.class));
                break;
            case R.id.login_newuser:
                startActivity(new Intent(this, Validate_phone.class));
                break;
            case R.id.login_login:
                if (ask.isChecked()) {
                    getinformation();
                    startActivity(new Intent().setClass(Login.this, Main_information.class));
                    finish();
                } else {
                    Toast.makeText(Login.this, "您需要同意服务条款才能使用本产品", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public String getinformation(int number){
        if(number==1){
            return username;
        }else{
            return password;
        }
    }

}
