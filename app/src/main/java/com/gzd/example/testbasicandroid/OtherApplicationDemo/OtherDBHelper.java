package com.gzd.example.testbasicandroid.OtherApplicationDemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gzd.example.testbasicandroid.R;

/**
 * Created by gzd on 2019/1/25 0025
 */
public class OtherDBHelper extends SQLiteOpenHelper {
    private String sql;
    public OtherDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        sql = context.getString(R.string.db_create_sq2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
