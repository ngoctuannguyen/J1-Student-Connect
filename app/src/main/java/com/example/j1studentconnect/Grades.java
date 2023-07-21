package com.example.j1studentconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

public class Grades extends AppCompatActivity {

    private ImageButton btnGradesHome, btnGradesSearch, btnGradesProfile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grades_search);
        ConstructGradesButton();
        ClickGradesButton();

    }

    private void ConstructGradesButton() {
        btnGradesHome = (ImageButton) findViewById(R.id.GradesHome);
        btnGradesSearch = (ImageButton) findViewById(R.id.GradesSearch);
        btnGradesProfile = (ImageButton) findViewById(R.id.GradesProfile);

    }

    private void ClickGradesButton() {

        btnGradesProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Grades.this, Profile.class));
            }
        });

        btnGradesHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Grades.this, MainActivity.class));
            }
        });

        btnGradesSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Grades.this, Search.class));
            }
        });

    }
}
