package com.gzd.example.testbasicandroid.tool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by gzd on 2019/1/24 0024
 */
public class ManifestReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("manifest broadcast");
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
