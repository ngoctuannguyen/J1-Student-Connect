package com.example.j1studentconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SearchActivity extends AppCompatActivity {

    private ImageButton btnSearchHome, btnSearchProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in4_search);

        btnSearchHome = (ImageButton) findViewById(R.id.greyHomeSearch);
        btnSearchProfile = (ImageButton) findViewById(R.id.greyProfileSearch);
        btnSearchHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this, MainActivity.class));
            }
        });

        btnSearchProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this, ProfileActivity.class));
            }
        });
    }
}