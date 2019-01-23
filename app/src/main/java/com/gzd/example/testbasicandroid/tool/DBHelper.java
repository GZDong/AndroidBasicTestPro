package com.gzd.example.testbasicandroid.tool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gzd.example.testbasicandroid.R;

/**
 * Created by gzd on 2019/1/24 0024
 */
public class DBHelper extends SQLiteOpenHelper {
    Context mContext;
    String createSQL;
    public DBHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
        createSQL = mContext.getString(R.string.db_create_sql);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
