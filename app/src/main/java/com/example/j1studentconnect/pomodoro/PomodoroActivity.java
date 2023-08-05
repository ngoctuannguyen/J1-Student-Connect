package com.example.j1studentconnect.pomodoro;

import android.animation.ObjectAnimator;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.j1studentconnect.R;
import com.example.j1studentconnect.tabsinmain.MainActivity;

import java.util.Date;

public class PomodoroActivity extends AppCompatActivity {

    public boolean quarterHourPress = false, halfHourPress = false, breakTime = false;

    public boolean timeRunning = false;
    public boolean fourtimes = false;
    public int cntRound = 1;
    RelativeLayout relativeLayout;
    TextView titlePomodoro, roundCount;
    TextView textRoundCount;
    ImageView relaxTuzki;
    ImageView hiTuzki;
    ImageView fightingTuzki;
    private TextView startButton, halfAnHour, quarterHour;
    private CountDownTimer pomodoroTimer = null;
    private ImageView backFromPomodoro;
    private long Endtime;
    private long TimeLeftInMillis;

    private long fadeInDuration,fadeOutDuration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);
        //createNotificationChannel();
        startButton = findViewById(R.id.start_button);

        backFromPomodoro = findViewById(R.id.backFromPomodoro);
        relativeLayout = findViewById(R.id.pomorodoLayout);
        textRoundCount = findViewById(R.id.RoundCount);

        fightingTuzki = findViewById(R.id.fightingtzukii);
        hiTuzki = findViewById(R.id.tuzki_say_hi);
        relaxTuzki = findViewById(R.id.tuzki_relax);
        fightingTuzki.setVisibility(View.GONE);
        relaxTuzki.setVisibility(View.GONE);
        ObjectAnimator shaking_hiTuzki = ObjectAnimator.ofFloat(hiTuzki, "translationX", 0f, -30f, 30f, 0f);
        shaking_hiTuzki.setDuration(1000);
        shaking_hiTuzki.setInterpolator(new AccelerateDecelerateInterpolator());
        shaking_hiTuzki.start();


        backFromPomodoro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PomodoroActivity.this, MainActivity.class));
                // Thiết lập animation khi mở Activity mới
                overridePendingTransition(R.anim.anim_pomodoro_in, R.anim.anim_pomodoro_out);

            }
        });

        //linearLayout.setBackgroundColor(Color.RED);
        halfAnHour = findViewById(R.id.halfAnHour);
        halfAnHour.setVisibility(View.GONE);
        quarterHour = findViewById(R.id.quaterAnHour);
        quarterHour.setVisibility(View.GONE);

        TextView textView = findViewById(R.id.timer_text_view);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        titlePomodoro = findViewById(R.id.TitlePomodoro);
        roundCount = findViewById(R.id.RoundCount);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!timeRunning) {
                    timeRunning = true;
                    startTimer();
                    startButton.setText("Dừng và làm lại cuộc đời");

                } else {
                    timeRunning = false;
                    cntRound = 1;
                    fadeOutDuration = 0;
                    fadeOut.setDuration(fadeOutDuration);
                    textView.startAnimation(fadeOut);
                    roundCount.setText("1 | 4");
                    startButton.setText("Vào bàn học thôi !!");
                    TextView timer_text_view = findViewById(R.id.timer_text_view);
                    timer_text_view.setText("0 : 10");
                    pomodoroTimer.cancel();
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

    private void startFadeInAnimation() {
        textRoundCount.setVisibility(View.VISIBLE);

        AlphaAnimation fadeInAnimation = new AlphaAnimation(0f, 1f);
        fadeInAnimation.setDuration(1000); // Thời gian thực hiện animation (1 giây)
        textRoundCount.startAnimation(fadeInAnimation);
    }

    public void startTimer() {
        TextView textView = findViewById(R.id.timer_text_view);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        long timerLength = 10 * 1000;
        Endtime = System.currentTimeMillis() + timerLength;
        fadeOutDuration = timerLength;
        fadeOut.setDuration(fadeOutDuration);
        textView.startAnimation(fadeOut);


        //linearLayout.setBackgroundColor(Color.RED);
        TextView timer_text_view = findViewById(R.id.timer_text_view);
        TextView tiltePomodoro = findViewById(R.id.TitlePomodoro);
        titlePomodoro.setText("Tập trung học bài nào !!!");
        relativeLayout.setBackgroundResource(R.drawable.bg_pinkorange);
        hiTuzki.setVisibility(View.GONE);
        relaxTuzki.setVisibility(View.GONE);
        fightingTuzki.setVisibility(View.VISIBLE);
        startZoomAnimation();
//        ObjectAnimator shaking = ObjectAnimator.ofFloat(fightingTuzki, "translationX", 0f, -30f, 30f, 0f);
//        shaking.setDuration(1500);
//        shaking.setRepeatCount(ObjectAnimator.INFINITE);
//        shaking.setInterpolator(new AccelerateDecelerateInterpolator());
//        shaking.start();
        pomodoroTimer = new CountDownTimer(timerLength, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //if (timeRunning && !fourtimes) {
                TimeLeftInMillis = millisUntilFinished;
                long minutes = millisUntilFinished / 1000 / 60;
                long seconds = millisUntilFinished / 1000 % 60;
                timer_text_view.setText(minutes + " : " + seconds);
                //}
            }

            @Override
            public void onFinish() {
//                Intent intent = new Intent(PomodoroActivity.this, PomodoroNoti.class);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(PomodoroActivity.this, 0, intent, 0);
                //AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                pomodoroTimer.cancel();
                //timeRunning = false;
                //AlertDialog.Builder builder = new AlertDialog.Builder(PomodoroActivity.this);
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

                // if (cntRound != 4)
                breakTime = true;
                showNotification();

                if (cntRound == 4) {
                    cntRound = 1;
                    timeRunning = false;
                    fourtimes = true;
                    //roundCount.setText(cntRound + " / 4");
                    quarterHour.setVisibility(View.VISIBLE);
                    halfAnHour.setVisibility(View.VISIBLE);
                    //halfHourPress = true;
                    chooseQuaterOrHalf();
                } else startBreakTimer();


            }
        };
        if (timeRunning) pomodoroTimer.start();
    }

    private void showNotification() {
        // Tạo đối tượng âm thanh từ tệp trong thư mục res/raw (trong đây tên là notification_sound)
        // Tạo đối tượng âm thanh từ tệp trong thư mục res/raw (trong đây tên là notification_sound)
        Uri soundUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notification_sound);

        Intent intent = new Intent(this, PomodoroActivity.class);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, "POMODORO")
                                                                        .setContentTitle("VNU Pomodoro Timer")
                                                                        .setContentText("Hết giờ học rồi, nghỉ ngơi chút nhé !!!!")
                                                                        .setSmallIcon(R.drawable.icon_learning_pomodoro)
                                                                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                                                                        .setSound(soundUri);

        if (breakTime == false)
            notification.setContentText("Nghỉ thế là đủ rồi, tiếp tục tập trung thôi !!!");

          //.setAutoCancel(true);
        //.setSound(Uri.parse("https://firebasestorage.googleapis.com/v0/b/mynotes-8b6d5.appspot.com/o/mixkit-scanning-sci-fi-alarm-905.wav?alt=media&token=5bebfd2b-3bc3-45a4-8a2d-acb2f3f0e182"));

        // Display the notification
        //NotificationManagerCompat notificationManager = NotificationManagerCompat.from(PomodoroActivity.this);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null)
            notificationManager.notify(getNotiID(), notification.build());
    }


    private void startBreakTimer() {
        long timerLength = 5 * 1000;
        fadeInDuration = timerLength;

        TextView textView = findViewById(R.id.timer_text_view);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeIn.setDuration(fadeInDuration);
        textView.startAnimation(fadeIn);

        //linearLayout.setBackgroundColor(Color.GREEN);
        titlePomodoro.setText("Nghỉ ngơi chút nhé !");
        relativeLayout.setBackgroundResource(R.color.bg_blue);
        hiTuzki.setVisibility(View.GONE);
        fightingTuzki.setVisibility(View.GONE);
        relaxTuzki.setVisibility(View.VISIBLE);
        ObjectAnimator shakingRelaxTuzki = ObjectAnimator.ofFloat(relaxTuzki, "translationX", 0f, -30f, 30f, 0f);
        shakingRelaxTuzki.setDuration(1000);
        shakingRelaxTuzki.setRepeatCount(ObjectAnimator.INFINITE);
        shakingRelaxTuzki.setInterpolator(new AccelerateDecelerateInterpolator());
        shakingRelaxTuzki.start();


        //if (timeRunning) {
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
                //boolean timerRunning = false;

                //if (!startButtonPress){
                cntRound++;
                roundCount.setText(cntRound + " | 4");
                startFadeInAnimation();
                //astartButtonPress = true;

                //}

                // startButtonPress = false;
                // roundCount.setText(cntRound + " / 4");

                //AlertDialog.Builder builder = new AlertDialog.Builder(PomodoroActivity.this);
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
                breakTime = false;
                showNotification();
                startTimer();
            }


        };
        if (timeRunning) pomodoroTimer.start();
    }

    private void chooseQuaterOrHalf() {

        roundCount.setText("1 | 4");
        titlePomodoro.setText("Nghỉ ngơi chút nhé !");
        //fourtimes = false;
        halfAnHour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView timer_text_view = findViewById(R.id.timer_text_view);
                //halfHourPress = true
                timer_text_view.setText("30 : 00");
                SystemClock.sleep(1000);
                halfAnHour.setVisibility(View.GONE);
                quarterHour.setVisibility(View.GONE);
                halfHourPress = true;
                quarterHourPress = false;
                timeRunning = true;
                startBreakTimer();
            }
        });

        quarterHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView timer_text_view = findViewById(R.id.timer_text_view);
                timer_text_view.setText("15 : 00");
                SystemClock.sleep(1000);
                quarterHour.setVisibility(View.GONE);
                halfAnHour.setVisibility(View.GONE);
                halfHourPress = true;
                quarterHourPress = false;
                timeRunning = true;
                startBreakTimer();
            }
        });


    }

    private void startZoomAnimation() {
        ScaleAnimation zoomAnimation = new ScaleAnimation(1f, 2f, 1f, 2f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        zoomAnimation.setDuration(1000); // Thời gian thực hiện animation
        fightingTuzki.startAnimation(zoomAnimation);
    }

    private int getNotiID() {
        return (int) new Date().getTime();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pomodoroTimer != null) pomodoroTimer.cancel();

    }

    private void updateCountDownText() {
        int minutes = (int) (TimeLeftInMillis / 1000) / 60;
        int seconds = (int) (TimeLeftInMillis / 1000) % 60;

        //String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        //mTextViewCountDown.setText(timeLeftFormatted);
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("millisleft", TimeLeftInMillis);
        editor.putBoolean("timeRunning", timeRunning);
        editor.putLong("endTime", Endtime);
        editor.apply();

        if (pomodoroTimer != null) pomodoroTimer.cancel();


    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        TimeLeftInMillis = preferences.getLong("millisleft", 600000);
        timeRunning = preferences.getBoolean("timeRunning", false);

        updateCountDownText();

        if (timeRunning) {

            Endtime = preferences.getLong("endTime", 0);
            TimeLeftInMillis = Endtime - System.currentTimeMillis();
            if (TimeLeftInMillis < 0) {
                TimeLeftInMillis = 0;
                timeRunning = false;
                updateCountDownText();

            } else startTimer();
        }

    }
}