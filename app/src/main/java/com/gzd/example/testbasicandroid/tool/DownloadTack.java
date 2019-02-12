package com.gzd.example.testbasicandroid.tool;

import android.os.AsyncTask;

/**
 * Created by gzd on 2019/2/12 0012
 */
public class DownloadTack extends AsyncTask<Void,Integer,String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {
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
