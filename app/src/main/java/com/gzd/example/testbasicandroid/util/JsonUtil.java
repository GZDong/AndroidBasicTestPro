package com.gzd.example.testbasicandroid.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gzd.example.testbasicandroid.pojo.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by gzd on 2019/2/2 0002
 */
public class JsonUtil {
    public static HashMap<String,String> parseJsonWithJSONObject(String response){
        HashMap<String,String> data = new HashMap<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                data.put("id", object.getString("id"));
                //...
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static List<Person> parseJsonWithGSON(String response){
        Gson gson = new Gson();
        List<Person> list = gson.fromJson(response,new TypeToken<List<Person>>(){}.getType());
        return list;
    }
}
