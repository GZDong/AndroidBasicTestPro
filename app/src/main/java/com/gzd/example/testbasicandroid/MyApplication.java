package com.gzd.example.testbasicandroid;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.gzd.example.testbasicandroid.OtherApplicationDemo.OtherDBHelper;

import org.litepal.LitePal;

/**
 * Created by gzd on 2019/1/24 0024
 */
public class MyApplication extends Application {
    private static SQLiteDatabase database;
    private BroadcastReceiver receiver;
    private IntentFilter filter;
    private NotificationManager notificationManager;
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        OtherDBHelper helper = new OtherDBHelper(this,getString(R.string.db_name_other),null,1);
        database = helper.getWritableDatabase();
        Log.e("application", "onCreate: ------------" );

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        filter = new IntentFilter();
        filter.addAction("goToMainUI");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String content = intent.getStringExtra("content");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
                    NotificationChannel channel = new NotificationChannel("channel1","download",NotificationManager.IMPORTANCE_HIGH);
                    channel.setDescription(content);
                    channel.setLightColor(Color.RED);
                    channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 600});
                    channel.enableLights(true);
                    channel.setBypassDnd(true);
                    channel.setShowBadge(true);
                    notificationManager.createNotificationChannel(channel);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context,1,new Intent(context,MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
                    Notification notification = new NotificationCompat.Builder(context,channel.getId())
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                            .setWhen(System.currentTimeMillis())
                            .setPriority(NotificationCompat.PRIORITY_MAX)
                            .setContentTitle("From me")
                            .setContentText(content)
                            .setContentIntent(pendingIntent)
                            .build();
                    notificationManager.notify(1,notification);
                    notificationManager.notify(2,notification);
                }
            }
        };
        registerReceiver(receiver, filter);
    }

    public static SQLiteDatabase getDatabase() {
        return database;
    }
}
