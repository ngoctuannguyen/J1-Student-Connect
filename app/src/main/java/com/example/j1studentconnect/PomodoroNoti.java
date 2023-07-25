package com.example.j1studentconnect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class PomodoroNoti extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "pomodoro_channel")
                .setSmallIcon(R.drawable.gold_medal)
                .setContentTitle("Pomodoro Timer")
                .setContentText("Đã hết một lượt rồi")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                //.setAutoCancel(true);
        //.setSound(Uri.parse("https://firebasestorage.googleapis.com/v0/b/mynotes-8b6d5.appspot.com/o/mixkit-scanning-sci-fi-alarm-905.wav?alt=media&token=5bebfd2b-3bc3-45a4-8a2d-acb2f3f0e182"));

        // Display the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, builder.build());
    }


}
