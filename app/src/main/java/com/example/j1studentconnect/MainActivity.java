package com.example.j1studentconnect;
//import State.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageButton btnSearch, btnProfile, btnCalendar,
                        btnCalendarHotkey, btnAvatar, btnX, btnGrades, btnCourses, btnRequest;
    private Button btnRecover;
    private CardView ConvenientCard;
    private TextView txtToday;
    private java.util.Calendar today = java.util.Calendar.getInstance();
    public static boolean recover = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstructLayout();
        ConstructButton();
        ClickButton();
    }

    private void ConstructTextView(){

        //txtRecover = (TextView) findViewById(R.id.recover);
        txtToday = (TextView) findViewById(R.id.today);

    }

    private void RenderToday(){
        String date;
        date = today.get(java.util.Calendar.DATE) + " Tháng " + (today.get(java.util.Calendar.MONTH) + 1) + ", " + today.get(java.util.Calendar.YEAR);
        txtToday.setText(date);
    }

    private void ConstructButton(){

        btnSearch = (ImageButton) findViewById(R.id.homeSearch);
        btnProfile = (ImageButton) findViewById(R.id.homeProfile);
        btnCalendar = (ImageButton) findViewById(R.id.TimeTable);
        btnCalendarHotkey = (ImageButton) findViewById(R.id.calendarHotKey);
        btnAvatar = (ImageButton) findViewById(R.id.dogAvt);
        btnX = (ImageButton) findViewById(R.id.x);
        btnGrades = (ImageButton) findViewById(R.id.grades);
        btnCourses = (ImageButton) findViewById(R.id.courses);
        btnRequest = (ImageButton) findViewById(R.id.request);


    }

    private void ConstructLayout(){

        ConvenientCard = (CardView) findViewById(R.id.convenientNoti);
        btnRecover = (Button) findViewById(R.id.recover);
        if (recover == false) {

            ConstructTextView();
            RenderToday();
        }
        else {
            ConvenientCard.setVisibility(View.GONE);
            btnRecover.setVisibility(View.VISIBLE);
        }

    }

    private void ClickButton(){
        btnGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Grades.class);
                startActivity(intent);
            }
        });
        btnCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Courses.class);
                startActivity(intent);
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Request.class);
                startActivity(intent);
            }
        });

        btnAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Calendar.class));
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Search.class));
            }
        });

        btnCalendarHotkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Calendar.class));
            }
        });

        btnX.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (recover == false){
                    recover = true;
                    ConvenientCard.setVisibility(View.GONE);
                    btnRecover.setVisibility(View.VISIBLE);
                }
            }
        });

        btnRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConvenientCard.setVisibility(View.VISIBLE);
                btnRecover.setVisibility(View.GONE);
                recover = false;
            }
        });

    }
}