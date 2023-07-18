package com.example.j1studentconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Calendar extends AppCompatActivity {

    private ImageButton btnTBHome, btnTBSearch, btnTBProfile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_set);

        btnTBHome = (ImageButton) findViewById(R.id.TimeTableHome);
        btnTBSearch = (ImageButton) findViewById(R.id.TimeTableSearch);
        btnTBProfile = (ImageButton) findViewById(R.id.TimeTableProfile);
        ClickButtonInCalen();
    }

    private void ClickButtonInCalen(){

        btnTBHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Calendar.this, MainActivity.class));
            }
        });

        btnTBSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Calendar.this, Search.class));
            }
        });

        btnTBProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Calendar.this, Profile.class));
            }
        });
    }


}
