package com.gzd.example.testbasicandroid.util;
import java.util.HashMap;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by gzd on 2019/2/2 0002
 */
public class OKHttpUtil {
    public void sendOKHttpRequestWithGet(final String url, Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void sendOKHttpRequestWithPost(String url, HashMap<String,String> data,Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("id",data.get("id"))
                .add("age",data.get("age"))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
