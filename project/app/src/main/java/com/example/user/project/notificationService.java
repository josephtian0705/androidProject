package com.example.user.project;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class notificationService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        super.onMessageReceived(remoteMessage);

        //      Intent intent = new Intent(this, newsActivity.class );
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
//        notificationBuilder.setContentTitle("Project Notification");
//        notificationBuilder.setContentText(remoteMessage.getNotification().getBody());
//        notificationBuilder.setAutoCancel(true);
//        notificationBuilder.setContentIntent(pendingIntent);
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, notificationBuilder.build());
        showingNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("REGISTER_TOKEN",s);
    }


    public void showingNotification(String notificationTitle, String notificationBody){

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "com.example.user.test";


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            //setting up notification channel
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification",
                    notificationManager.IMPORTANCE_DEFAULT);


            //set the detail of notification
            notificationChannel.setDescription("Project Notification Channel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationManager.createNotificationChannel(notificationChannel);

        }

        //Build the notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);


        //set the style of notification
        notificationBuilder.setAutoCancel(true).
                setDefaults(Notification.DEFAULT_ALL).
                setWhen(System.currentTimeMillis()).
                setSmallIcon(R.drawable.notification).
                setContentTitle(notificationTitle).
                setContentText(notificationBody).
                setContentInfo("Info");

        notificationManager.notify(new Random().nextInt(),notificationBuilder.build());
    }
}
