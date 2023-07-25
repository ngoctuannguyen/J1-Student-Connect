package com.example.j1studentconnect;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

public class PomodoroActivity extends AppCompatActivity {

    private Button startButton;
    private CountDownTimer pomodoroTimer = null;
    boolean timeRunning = false;
    TextView titlePomodoro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);
        createNotificationChannel();
        startButton = findViewById(R.id.start_button);
        titlePomodoro = findViewById(R.id.TitlePomodoro);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!timeRunning){
                    startTimer();
                    timeRunning = true;
                    startButton.setText("Pause");
                }
                else {
                    pomodoroTimer.cancel();
                    timeRunning = false;
                    startButton.setText("Start");
                }
            }
        });
    }

    private void startTimer() {
        long timerLength = 10 * 1000;
        TextView timer_text_view = findViewById(R.id.timer_text_view);
        pomodoroTimer = new CountDownTimer(timerLength, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = millisUntilFinished / 1000 / 60;
                long seconds = millisUntilFinished / 1000 % 60;
                timer_text_view.setText(minutes + " : " + seconds);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(PomodoroActivity.this, PomodoroNoti.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(PomodoroActivity.this, 0, intent, 0);
                //AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                pomodoroTimer.cancel();
                timeRunning = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(PomodoroActivity.this);
                builder.setMessage("Nghỉ ngơi đã")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startBreakTimer();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startTimer();
                                timeRunning = true;
                                startButton.setText("Pause");
                            }
                        }).show();

                //showNotification();
            }
        };
        pomodoroTimer.start();
    }

//    private void showNotification() {
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "pomodoro_channel")
//                .setSmallIcon(R.drawable.gold_medal)
//                .setContentTitle("Pomodoro Timer")
//                .setContentText("Your Pomodoro session is finished!")
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setAutoCancel(true);
//                //.setSound(Uri.parse("https://firebasestorage.googleapis.com/v0/b/mynotes-8b6d5.appspot.com/o/mixkit-scanning-sci-fi-alarm-905.wav?alt=media&token=5bebfd2b-3bc3-45a4-8a2d-acb2f3f0e182"));
//
//        // Display the notification
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(PomodoroActivity.this);
//        notificationManager.notify(200, builder.build());
//    }


    private void startBreakTimer() {
        long timerLength = 5 * 1000;
        titlePomodoro.setText("Nghỉ ngơi chút nhé !");
        pomodoroTimer = new CountDownTimer(timerLength, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = millisUntilFinished / 1000 / 60;
                long seconds = millisUntilFinished / 1000 % 60;
                TextView timer_text_view = findViewById(R.id.timer_text_view);
                timer_text_view.setText(minutes + ":" + seconds);
            }

            @Override
            public void onFinish() {
                if (pomodoroTimer != null) {
                    pomodoroTimer.cancel();
                }
                boolean timerRunning = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(PomodoroActivity.this);
                builder.setMessage("Giữ vững tinh thần nha !!!")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startTimer();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(PomodoroActivity.this, "Lần sau tiếp tục nhé !!!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        };
        pomodoroTimer.start();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_desc);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("abc", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}