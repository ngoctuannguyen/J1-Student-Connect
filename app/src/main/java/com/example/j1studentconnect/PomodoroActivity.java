package com.example.j1studentconnect;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
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
import java.util.Date;

public class PomodoroActivity extends AppCompatActivity {

    private Button startButton, resetTimer;
    private CountDownTimer pomodoroTimer = null;
    boolean timeRunning = false;
    TextView titlePomodoro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);
        //createNotificationChannel();
        startButton = findViewById(R.id.start_button);
        resetTimer = findViewById(R.id.resetTimer);
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

        resetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void startTimer() {
        long timerLength = 10 * 1000;
        TextView timer_text_view = findViewById(R.id.timer_text_view);
        TextView tiltePomodoro = findViewById(R.id.TitlePomodoro);
        titlePomodoro.setText("Tập trung học bài nào !!!");
        pomodoroTimer = new CountDownTimer(timerLength, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = millisUntilFinished / 1000 / 60;
                long seconds = millisUntilFinished / 1000 % 60;
                timer_text_view.setText(minutes + " : " + seconds);
            }

            @Override
            public void onFinish() {
//                Intent intent = new Intent(PomodoroActivity.this, PomodoroNoti.class);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(PomodoroActivity.this, 0, intent, 0);
                //AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                pomodoroTimer.cancel();
                timeRunning = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(PomodoroActivity.this);
//                builder.setMessage("Nghỉ ngơi đã")
//                        .setPositiveButton("Okela", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                startBreakTimer();
//                            }
//                        })
//                        .setNegativeButton("Không muốn nghỉ đâu", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                startTimer();
//                                timeRunning = true;
//                                startButton.setText("Pause");
//                            }
//                        }).show();

                showNotification();
                startBreakTimer();
            }
        };
        pomodoroTimer.start();
    }

    private void showNotification() {

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, "POMODORO")
                .setContentTitle("VNU Pomodoro Timer")
                .setContentText("Hết giờ học rồi, nghỉ ngơi chút nhé !!!!")
                .setSmallIcon(R.drawable.icon_learning_pomodoro)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
                //  .setAutoCancel(true);
                //.setSound(Uri.parse("https://firebasestorage.googleapis.com/v0/b/mynotes-8b6d5.appspot.com/o/mixkit-scanning-sci-fi-alarm-905.wav?alt=media&token=5bebfd2b-3bc3-45a4-8a2d-acb2f3f0e182"));

        // Display the notification
        //NotificationManagerCompat notificationManager = NotificationManagerCompat.from(PomodoroActivity.this);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null)
                notificationManager.notify(getNotiID(), notification.build());
    }


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
//                builder.setMessage("Giữ vững tinh thần nha !!!")
//                        .setPositiveButton("Oki", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                startTimer();
//                            }
//                        })
//                        .setNegativeButton("Hôm nay học vậy là đủ rồi", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(PomodoroActivity.this, "Lần sau tiếp tục nhé !!!", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .show();
                startTimer();
            }

        };
        pomodoroTimer.start();
    }

    private int getNotiID(){
        return (int) new Date().getTime();
    }

}