package com.gzd.example.testbasicandroid.OtherApplicationDemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.gzd.example.testbasicandroid.MyApplication;
import com.gzd.example.testbasicandroid.R;

public class MyContentProvider extends ContentProvider {
    private static SQLiteDatabase database;
    private static UriMatcher matcher;
    public final static String AHTHORITY = "otheractivityprovider";
    public final static int TAG1 = 1;
    public final static int TAG2 = 2;
    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AHTHORITY,"OtherPerson",TAG1);
        matcher.addURI(AHTHORITY,"OtherPerson/name",TAG2);

    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        OtherDBHelper helper = new OtherDBHelper(getContext(),getContext().getString(R.string.db_name_other),null,1);
        database = helper.getWritableDatabase();
        Log.e("provider", "onCreate: ..........");
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows
        int rowNum;
        switch (matcher.match(uri)) {
            case TAG1:
            case TAG2:
                rowNum = database.delete("OtherPerson",selection,selectionArgs);
            break;
            default:
                rowNum = 0;
                Toast.makeText(getContext(),"no matcher",Toast.LENGTH_LONG).show();
        }
        return rowNum;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        Uri uriReturn = null;
        switch (matcher.match(uri)) {
            case TAG1:
            case TAG2:
                long newId = database.insert("OtherPerson",null,values);
                uriReturn = Uri.parse("content://"+AHTHORITY + "OtherPerson/" + newId);
            break;
            default:
                Toast.makeText(getContext(),"no matcher",Toast.LENGTH_LONG).show();
        }
        return uriReturn;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        Cursor cursor = null;
        switch (matcher.match(uri)) {
            case TAG1:
                cursor = database.query("OtherPerson",null,null,null,null,null,sortOrder);
                break;
            case TAG2:
                cursor = database.query("OtherPerson",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                Toast.makeText(getContext(),"no matcher",Toast.LENGTH_LONG).show();
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        int updateNum = 0;
        switch (matcher.match(uri)) {
            case TAG1:
            case TAG2:
                updateNum = database.update("OtherPerson",values,selection,selectionArgs);
            break;
            default:
                Toast.makeText(getContext(),"no matcher",Toast.LENGTH_LONG).show();
        }
        return updateNum;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        String str = null;
        switch (matcher.match(uri)) {  //游标的区别而已
            case TAG1:
                str = "vnd.android.cursor.dir/vnd." + AHTHORITY + ".OtherPerson";
            break;
            case TAG2:
                str = "vnd.android.cursor.item/vnd." + AHTHORITY + ".OtherPerson";
                break;
            default:
        }
        return str;
    }
}
