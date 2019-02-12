package com.gzd.example.testbasicandroid.tool;

import android.os.AsyncTask;

/**
 * Created by gzd on 2019/2/12 0012
 */
public class DownloadTack extends AsyncTask<Void,Integer,String> {   //第一个参数用于execute
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {
        //publishProgress 去onProgressUpdate，return去onPostExecute
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }
}
