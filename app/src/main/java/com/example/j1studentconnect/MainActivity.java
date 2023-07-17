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
                        btnCalendarHotkey, btnAvatar, btnX;
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
        txtToday = findViewById(R.id.today);

    }

    private void RenderToday(){
        String date;
        date = today.get(java.util.Calendar.DATE) + " Tháng " + (today.get(java.util.Calendar.MONTH) + 1) + ", " + today.get(java.util.Calendar.YEAR);
        txtToday.setText(date);
    }

    private void ConstructButton(){

        btnSearch = findViewById(R.id.homeSearch);
        btnProfile = findViewById(R.id.homeProfile);
        btnCalendar = findViewById(R.id.TimeTable);
        btnCalendarHotkey =  findViewById(R.id.calendarHotKey);
        btnAvatar = findViewById(R.id.dogAvt);
        btnX = findViewById(R.id.x);

    }

    private void ConstructLayout(){

        ConvenientCard = findViewById(R.id.convenientNoti);
        btnRecover = findViewById(R.id.recover);
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
                Intent intentBefore = getIntent();
                String user_name = intentBefore.getStringExtra("name");
                String user_email = intentBefore.getStringExtra("email");
                String user_id = intentBefore.getStringExtra("student_id");
                String user_password = intentBefore.getStringExtra("password");
                String user_gender = intentBefore.getStringExtra("gender");
                String user_class = intentBefore.getStringExtra("student_class");
                String user_birthday = intentBefore.getStringExtra("birthday");

                Intent intent = new Intent(MainActivity.this, Profile.class);
                intent.putExtra("student_id", user_id);
                intent.putExtra("password", user_password);
                intent.putExtra("name", user_name);
                intent.putExtra("gender", user_gender);
                intent.putExtra("email", user_email);
                intent.putExtra("student_class", user_class);
                intent.putExtra("birthday", user_birthday);
                startActivity(intent);
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