package com.example.connectivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


import org.apache.http.params.HttpConnectionParams;

/**
 * Created by 段鑫淼 on 2016/11/8.
 */

public class HttpUtils {
    /**
     * Post方式提交到服务器
     * @param json	json数据
     * @param handler	handler消息处理机制
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static void httpPostMethod(String url, JSONObject json, Handler handler)
            throws UnsupportedEncodingException, IOException,
            ClientProtocolException {

        HttpParams params = new BasicHttpParams();
        //设置连接超时时间
        HttpConnectionParams.setConnectionTimeout(params, 50000);
        HttpClient client = new DefaultHttpClient(params);

        //提交json数据到服务器
        HttpPost request = new HttpPost(url);
        StringEntity se = new StringEntity(json.toString(), "UTF-8");  //将json对象绑定到请求
        se.setContentEncoding("UTF-8");
        se.setContentType("application/json");
        request.setEntity(se);
        request.setHeader("json", json.toString());
        HttpResponse response = client.execute(request);   //发送请求

        //获取服务器返回的数据
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){

            String res = EntityUtils.toString(response.getEntity(), "UTF-8"); //得到一个应答的字符串，这里也是用json格式保存的数据
            Log.d("httpResponse", res);
            Message msg = new Message();
            msg.what = 0;
            Bundle bundle = new Bundle();
            bundle.putString("res", res);
            msg.setData(bundle);
            handler.sendMessage(msg);
        }
    }
}


