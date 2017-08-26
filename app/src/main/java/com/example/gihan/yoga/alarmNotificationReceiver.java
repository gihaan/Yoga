package com.example.gihan.yoga;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.example.gihan.yoga.R;

/**
 * Created by Gihan on 8/24/2017.
 */

public class alarmNotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);

        builder.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL).setWhen(System.currentTimeMillis()).
                setSmallIcon(R.mipmap.ic_launcher).setContentTitle(" it is time").setContentText("Time to Training").
                setContentInfo("Info");

        NotificationManager manager=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.notify(1,builder.build());
    }
}
