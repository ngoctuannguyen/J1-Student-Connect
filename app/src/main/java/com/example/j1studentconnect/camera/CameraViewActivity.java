package com.example.j1studentconnect.camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.j1studentconnect.R;

public class CameraViewActivity extends AppCompatActivity {

    ImageView imgView;
    Button btnCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imgView = findViewById(R.id.cameraCapture);
        btnCapture = findViewById(R.id.cameraBtn);

        if (ContextCompat.checkSelfPermission(CameraViewActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(CameraViewActivity.this, new String[]{android.Manifest.permission.CAMERA}, 10);
        }

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 101){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(bitmap);
        }

    }
}