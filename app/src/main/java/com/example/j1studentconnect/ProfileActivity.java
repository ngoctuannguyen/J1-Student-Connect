package com.example.j1studentconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton btnHomeProfile, btnSearchProfile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnHomeProfile = (ImageButton) findViewById(R.id.homeProfile);
        btnSearchProfile = (ImageButton) findViewById(R.id.searchProfile);

        btnHomeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

        btnSearchProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, SearchActivity.class));
            }
        });

    }
}
