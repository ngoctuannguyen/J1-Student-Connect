package com.example.j1studentconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RequestProcessing extends AppCompatActivity {

    private ImageButton btnRPHome, btnRPSearch, btnRPProfile;
    private LinearLayout btnRequestAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_request);

        btnRPHome = (ImageButton) findViewById(R.id.RequestProcessHome);
        btnRPSearch = (ImageButton) findViewById(R.id.RequestProcessSearch);
        btnRPProfile = (ImageButton) findViewById(R.id.RequestProcessProfile);
        btnRequestAdd = (LinearLayout) findViewById(R.id.add_request_bar);
        ClickButtonInRequestProcessing();
    }

    private void ClickButtonInRequestProcessing() {

        btnRequestAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestProcessing.this, RequestAdd.class));
            }
        });
        btnRPHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestProcessing.this, MainActivity.class));
            }
        });

        btnRPSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestProcessing.this, Search.class));
            }
        });

        btnRPProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestProcessing.this, Profile.class));
            }
        });
    }
}