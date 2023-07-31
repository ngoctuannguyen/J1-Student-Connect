package com.example.j1studentconnect.textrecognition;

import static android.Manifest.permission.CAMERA;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.j1studentconnect.R;
import com.example.j1studentconnect.camera.CameraViewActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
//import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import com.google.mlkit.vision.text.internal.TextRecognizerOptionsUtils;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
//import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

public class ScannerActivity extends AppCompatActivity {

    private ImageView captureTV;
    private TextView resultTV;
    private Button snapBTN, detectBTN;
    private Bitmap bitmap;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        captureTV = findViewById(R.id.idIVImgCapture);
        resultTV = findViewById(R.id.idTVDetectedText);
        snapBTN = findViewById(R.id.snapBTN);
        detectBTN = findViewById(R.id.detectTextbtn);

        detectBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detectText();
            }
        });

        snapBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission())
                    captureImage();
                else requestPermission();
            }
        });
    }

    private boolean checkPermission(){

        return ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED;

    }

    private void requestPermission(){
        int PERMISSION_CODE = 200;
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0){
            boolean cameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if (cameraPermission){
                Toast.makeText(this, "Permission ...", Toast.LENGTH_SHORT).show();
                captureImage();
            }
        }
    }

    private void captureImage() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null)
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            captureTV.setImageBitmap(bitmap);
        }
    }

    private void detectText() {
        InputImage image = InputImage.fromBitmap(bitmap, 0);
        TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        Task<Text> result = recognizer.process(image).addOnSuccessListener(new OnSuccessListener<Text>() {
            @Override
            public void onSuccess(Text text) {
                StringBuilder result = new StringBuilder();
                for (Text.TextBlock block: text.getTextBlocks()){

                    String blockText = block.getText();
                    Point[] blockCornerPoint = block.getCornerPoints();
                    Rect blockFrame = block.getBoundingBox();
                    for (Text.Line line: block.getLines()){
                        String lineTExt = line.getText();
                        Point[] lineCornerPoint = line.getCornerPoints();
                        Rect linRect = line.getBoundingBox();
                        for (Text.Element element: line.getElements()){
                            String elementText = element.getText();
                            result.append(elementText);
                        }
                        resultTV.setText(blockText);
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ScannerActivity.this, "Failed to detect text", Toast.LENGTH_SHORT).show();
            }
        });
    }
}