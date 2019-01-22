package com.gzd.example.testbasicandroid.tool;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/1/23 0023
 */
public class ActivityController {
    private static ArrayList<Activity> actList =  new ArrayList<>();

    public static void addAct(Activity activity){
        actList.add(activity);
    }

    public static void deleteAct(Activity activity){
        actList.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : actList){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
