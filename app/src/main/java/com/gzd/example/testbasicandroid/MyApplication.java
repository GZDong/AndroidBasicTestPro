package com.gzd.example.testbasicandroid;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gzd.example.testbasicandroid.OtherApplicationDemo.OtherDBHelper;

import org.litepal.LitePal;

/**
 * Created by gzd on 2019/1/24 0024
 */
public class MyApplication extends Application {
    private static SQLiteDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        OtherDBHelper helper = new OtherDBHelper(this,getString(R.string.db_name_other),null,1);
        database = helper.getWritableDatabase();
        Log.e("application", "onCreate: ------------" );
    }

    public static SQLiteDatabase getDatabase() {
        return database;
    }
}
