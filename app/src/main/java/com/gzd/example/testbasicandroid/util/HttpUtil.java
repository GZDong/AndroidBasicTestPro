package com.gzd.example.testbasicandroid.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by gzd on 2019/2/2 0002
 */
public class HttpUtil {

    private static HttpURLConnection init(URL url, String way,CallBack callBack){
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(way);
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
            callBack.onFailure(e);
        }
        return null;
    }
    public static void sendHttpGetRequest(final URL url,final CallBack callBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = init(url,"GET",callBack);
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    while (reader.readLine()!=null){
                        response.append(reader.readLine());
                    }
                    callBack.onResponse(response.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (reader != null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    public static void sendHttpPostRequest(final URL url,final HashMap<String,String> data,final CallBack callBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = init(url,"POST",callBack);
                DataOutputStream writer = null;
                try {
                    writer = new DataOutputStream(connection.getOutputStream());
                    StringBuilder form = new StringBuilder();
                    int size = data.size();
                    for (String key : data.keySet()){
                        form.append(key + "=" + data.get(key));
                        size--;
                        if (size!=0){
                            form.append("&");
                        }
                    }
                    writer.writeBytes(form.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (writer!=null){
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    interface CallBack{
        void onResponse(String response);
        void onFailure(Exception e);
    }
}
