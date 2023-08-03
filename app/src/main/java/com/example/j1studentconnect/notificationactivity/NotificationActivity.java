package com.example.j1studentconnect.notificationactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.j1studentconnect.R;
import com.example.j1studentconnect.searchtab.Search;
import com.example.j1studentconnect.tabsinmain.MainActivity;

public class NotificationActivity extends AppCompatActivity {

    private ImageButton btnHomeNoti, btnSearchNoti, btnProfileNoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ConstructButtonNoti();
        ClickButtonNoti();
    }

    private void ConstructButtonNoti() {
        btnHomeNoti = (ImageButton) findViewById(R.id.HomeNoti);
        btnSearchNoti = (ImageButton) findViewById(R.id.SearchNoti);
        btnProfileNoti = (ImageButton) findViewById(R.id.ProfileNoti);

    }

    private void ClickButtonNoti() {

        btnHomeNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotificationActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.anim_pomodoro_in, R.anim.anim_pomodoro_out);
            }
        });

        btnSearchNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotificationActivity.this, Search.class));
                overridePendingTransition(R.anim.anim_activity_left_to_right_out, R.anim.anim_activity_left_to_right_in);
            }
        });

//        btnProfileNoti.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(NotificationActivity.this, Profile.class));
//            }
//        });

    }


}