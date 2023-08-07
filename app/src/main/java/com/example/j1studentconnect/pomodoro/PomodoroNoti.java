package com.example.j1studentconnect.pomodoro;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.j1studentconnect.R;
import com.example.j1studentconnect.pomodoro.PomodoroActivity;

public class PomodoroNoti extends Application {

    private static final String CHANNEL_ID = "POMODORO";
    private static final String CHANNEL_ID2 ="POMODORO2";
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }
    private void createNotificationChannel() {



        // Tạo NotificationChannel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            // Tạo đối tượng âm thanh từ tệp trong thư mục res/raw (trong đây tên là notification_sound)
            Uri soundUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notification_sound_work);


            //Notification_Work
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_desc);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.setSound(soundUri, attributes);

            // Tạo đối tượng âm thanh từ tệp trong thư mục res/raw (trong đây tên là notification_sound)
            Uri soundUri2 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notification_sound_relax);
            //Notification_Relax
            CharSequence name_2 = getString(R.string.channel_name_2);
            String description_2 = getString(R.string.channel_desc2);
            int importance_2 = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel_2 = new NotificationChannel(CHANNEL_ID2, name, importance);
            channel_2.setDescription(description_2);
            channel_2.setSound(soundUri2, attributes);

            // Đăng ký kênh với hệ thống
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
                notificationManager.createNotificationChannel(channel_2);
            }
        }
    }



}