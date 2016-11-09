package com.example.connectivity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static java.lang.Integer.parseInt;

/**
 * Created by 段鑫淼 on 2016/11/8.
 */

public class Controler {
    private Handler handler;
    private String url = "http://192.168.0.188";
    private int success ;

    public int getLoginInformation() {

        //将视图层封装的数据接收、处理、传递给模型层
         final MyValues myValues = new MyValues();
        new Thread() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                super.run();
                try {
                    JSONObject json = new JSONObject();   //封装一个json对象以便传递
                    json.put("UserName", myValues.getLogin_Username());
                    json.put("PassWord", myValues.getLogin_Password());
                    //						httpPostMethod(json);
                    HttpUtils.httpPostMethod(url, json, handler);   //传递给模型层
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Log.d("json", "解析JSON出错");
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();
        //接收子线程中传来的数据，用与刷新UI
        handler = new Handler() {  //要加os里的handler这个包  不然没有方法
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        try {
                            String res = msg.getData().getString("res");  //获取res中传递的数据
                            JSONObject result = new JSONObject(res);
                            success = parseInt(result.getString("success"));  //success0为成功

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    default:
                        break;
                }
            }

        };
        return success;
    }

}

