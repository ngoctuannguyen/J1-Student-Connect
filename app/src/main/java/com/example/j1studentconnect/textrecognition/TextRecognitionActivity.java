package com.example.j1studentconnect.textrecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.j1studentconnect.R;

public class TextRecognitionActivity extends AppCompatActivity {

    Button btnCapture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_recognition);
        btnCapture = findViewById(R.id.captureTextbtn);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TextRecognitionActivity.this, ScannerActivity.class));
            }
        });
    }
}