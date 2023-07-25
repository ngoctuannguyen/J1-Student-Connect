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
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.Date;

public class PomodoroActivity extends AppCompatActivity {

    private Button startButton, halfAnHour, quarterHour;
    private CountDownTimer pomodoroTimer = null;
    boolean timeRunning = false;
    TextView titlePomodoro, roundCount;

    public static boolean quarterHourPress = false, halfHourPress = false, startButtonPress = false;

    //LinearLayout linearLayout = findViewById(R.id.layoutPomodoro);
    int cntRound = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);
        //createNotificationChannel();
        startButton = findViewById(R.id.start_button);
        //linearLayout.setBackgroundColor(Color.RED);
        halfAnHour = findViewById(R.id.halfAnHour);
        halfAnHour.setVisibility(View.GONE);
        quarterHour = findViewById(R.id.quaterAnHour);
        quarterHour.setVisibility(View.GONE);

        titlePomodoro = findViewById(R.id.TitlePomodoro);
        roundCount = findViewById(R.id.RoundCount);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!timeRunning){
                    timeRunning = true;
                    startTimer();
                    startButton.setText("Dừng và làm lại cuộc đời");
                }
                else {
                    pomodoroTimer.cancel();
                    timeRunning = false;
                    cntRound = 1;
                    roundCount.setText("1 / 4");
                    startButton.setText("Vào bàn học thôi !!");
                    TextView timer_text_view = findViewById(R.id.timer_text_view);
                    timer_text_view.setText("0 : 10");
                }
            }
        });

    }

    private void resetTimer() {
        TextView timer_text_view = findViewById(R.id.timer_text_view);
        timer_text_view.setText("0 : 10");
        startButton.setText("Vào bàn học thôi !!");
        timeRunning = false;
        pomodoroTimer.cancel();
    }

    public void startTimer() {
        long timerLength = 10 * 1000;
        //linearLayout.setBackgroundColor(Color.RED);
        TextView timer_text_view = findViewById(R.id.timer_text_view);
        TextView tiltePomodoro = findViewById(R.id.TitlePomodoro);
        titlePomodoro.setText("Tập trung học bài nào !!!");
        if (timeRunning)
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
                //timeRunning = false;
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

        Intent intent = new Intent(this, PomodoroActivity.class);

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
        //linearLayout.setBackgroundColor(Color.GREEN);
        titlePomodoro.setText("Nghỉ ngơi chút nhé !");
        //if (timeRunning)
            pomodoroTimer = new CountDownTimer(timerLength, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long minutes = millisUntilFinished / 1000 / 60;
                    long seconds = millisUntilFinished / 1000 % 60;
                    TextView timer_text_view = findViewById(R.id.timer_text_view);
                    timer_text_view.setText(minutes + " : " + seconds);
                }

                @Override
                public void onFinish() {
                    if (pomodoroTimer != null) {
                        pomodoroTimer.cancel();
                    }
                    boolean timerRunning = false;

                    if (cntRound == 4){
                        cntRound = 1;
                        roundCount.setText(cntRound + " / 4");
                        quarterHour.setVisibility(View.VISIBLE);
                        halfAnHour.setVisibility(View.VISIBLE);
                        chooseQuaterOrHalf();
                        quarterHour.setVisibility(View.GONE);
                        halfAnHour.setVisibility(View.GONE);
                    }

                    //if (!startButtonPress){
                        cntRound++;
                        roundCount.setText(cntRound + " / 4");
                        startButtonPress = true;
                    //}

                   // startButtonPress = false;
                   // roundCount.setText(cntRound + " / 4");

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

    private void chooseQuaterOrHalf() {

        halfAnHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView timer_text_view = findViewById(R.id.timer_text_view);
                //halfHourPress = true;
                timer_text_view.setText("30 : 00");
            }
        });

        quarterHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView timer_text_view = findViewById(R.id.timer_text_view);
                timer_text_view.setText("15 : 00");
            }
        });

        startTimer();

    }

    private int getNotiID(){
        return (int) new Date().getTime();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pomodoroTimer != null)
            pomodoroTimer.cancel();

    }
}