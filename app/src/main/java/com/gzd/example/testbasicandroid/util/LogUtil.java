package com.gzd.example.testbasicandroid.util;

import android.util.Log;

/**
 * Created by gzd on 2019/2/13 0013
 */
public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int CLOSE = 6;
    public static final int CURRENT_LEVEL = VERBOSE;
    public static void v(String tag,String msg){
        if (CURRENT_LEVEL <= VERBOSE){
            Log.v(tag,msg);
        }
    }

    public static void d(String tag,String msg){
        if (CURRENT_LEVEL <= DEBUG){
            Log.d(tag, msg);
        }
    }
    public static void i(String tag,String msg){
        if (CURRENT_LEVEL <= INFO){
            Log.i(tag, msg);
        }
    }
    public static void w(String tag,String msg){
        if (CURRENT_LEVEL <= WARN){
            Log.w(tag, msg );
        }
    }
    public static void e(String tag,String msg){
        if (CURRENT_LEVEL <= ERROR){
            Log.e(tag, msg );
        }
    }
}
