package com.example.j1studentconnect.facerecognition;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.j1studentconnect.HistogramComputing;
import com.example.j1studentconnect.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;

import java.util.List;

public class FaceRecognitionActivity extends AppCompatActivity {

    private ImageView originalImageIv, croppedImageIv;
    private Button detectFaceBtn;

    private static final String TAG = "FACE_DETECT_TAG";

    private static final int SCALING_FACTOR = 10;

    private FaceDetector detector;

    private static boolean img1 = true;
    Bitmap cropBitmap2 = null, cropBitmap1 = null;

    //double chiSquare = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_recognition);

        //init UI Views
        originalImageIv = findViewById(R.id.origin);
        detectFaceBtn = findViewById(R.id.detectFaceBtn);
        croppedImageIv = findViewById(R.id.croppedImageIv);

        FaceDetectorOptions realTimeFdo = new FaceDetectorOptions.Builder()
                .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                .build();

        //init FaceDetectorObj
        detector = FaceDetection.getClient();

        //handle click
        detectFaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //from drawable
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.thay_trinh_4);
                Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.thay_trinh_3);
                //bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.thay_trinh_2);

                //Bitmap from Uri, in case to detect face from mage picked from gallery
//                Uri imageUri = null;
//                try {
//                    Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//                //Bitmap from ImageView, in case your image is in ImageViw may be got from URL/Web
//                BitmapDrawable bitmapDrawable = (BitmapDrawable) originalImageIv.getDrawable();
//                Bitmap bitmap1 = bitmapDrawable.getBitmap();

                analyzePhoto(bitmap);
                //img1 = false;
                analyzePhoto(bitmap1);

            }


        });
    }


    private void analyzePhoto(Bitmap bitmap) {

        Log.d(TAG, "analyzePhoto");

        //Get smaller Bitmap to do analyze process faster
        Bitmap smallerBitmap = Bitmap.createScaledBitmap(
                bitmap,
                bitmap.getWidth() / SCALING_FACTOR,
                bitmap.getHeight() / SCALING_FACTOR,
                false
        );

        //Get input image using bitmap, you may use fromUri method
        InputImage intputImage = InputImage.fromBitmap(smallerBitmap, 0);

        //Start detection
        detector.process(intputImage)
                .addOnSuccessListener(new OnSuccessListener<List<Face>>() {
                    @Override
                    public void onSuccess(List<Face> faces) {
                        //can be multiple faces detected from an image, manage them using loop from List<Face> faces
                        Log.d(TAG, "on Success: No of faces detected" + faces.size());
                        for (Face face : faces) {
                            //Get detected face as rectangle
                            Rect rect = face.getBoundingBox();
                            rect.set(rect.left * SCALING_FACTOR,
                                    rect.top * (SCALING_FACTOR - 1),
                                    rect.right * SCALING_FACTOR,
                                    rect.bottom * SCALING_FACTOR + 90);
                        }
                        cropDetectedFaces(bitmap, faces);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Detection failed
                        Log.e(TAG, "onFailed ", e);
                        Toast.makeText(FaceRecognitionActivity.this, "Detection failed due to" + e.getMessage(), Toast.LENGTH_SHORT);
                    }
                });

    }


    private void cropDetectedFaces(Bitmap bitmap, List<Face> faces) {

        Log.d(TAG, "cropDetectedFaces: ");
        //Face was detected, now we will crop the face part of the image
        //there can be multiple faces, you can use loop to manage each (the first face)
        Rect rect = faces.get(0).getBoundingBox();

        int x = Math.max(rect.left, 0);
        int y = Math.max(rect.top, 0);
        int width = rect.width();
        int height = rect.height();

        Bitmap croppedBitmap = Bitmap.createBitmap(
                bitmap,
                x,
                y,
                (x + width > bitmap.getWidth()) ? bitmap.getWidth() + x : width,
                (y + height > bitmap.getHeight()) ? bitmap.getHeight() - y : height
        );

        //set the cropped bitmap to imageview
        //Bitmap cropBitmap1 = croppedBitmap, cropBitmap2 = croppedBitmap;
        if (img1) {
            cropBitmap1 = croppedBitmap;
            croppedImageIv.setImageBitmap(croppedBitmap);
            img1 = false;
            //return croppedBitmap;
        } else {
            cropBitmap2 = croppedBitmap;
            originalImageIv.setImageBitmap(croppedBitmap);
            img1 = true;
            HistogramCalculate(cropBitmap1, cropBitmap2);
        }

    }

    private void HistogramCalculate(Bitmap bm1, Bitmap bm2) {

        HistogramComputing histogramComputing = new HistogramComputing();
        int[] histogram1 = histogramComputing.HistogramComputing(bm1);
        int[] histogram2 = histogramComputing.HistogramComputing(bm2);
        int totalObservations1 = 0, totalObservations2 = 0;
        for (int i = 0; i < histogram1.length; i++) {
            totalObservations1 += histogram1[i];
            totalObservations2 += histogram2[i];
        }
        //Toast.makeText(this, "" + totalObservations2, Toast.LENGTH_SHORT).show();
        double[] expectedFrequencies1 = new double[histogram1.length];
        double[] expectedFrequencies2 = new double[histogram2.length];

        for (int i = 0; i < histogram1.length; i++) {
            expectedFrequencies1[i] = (double) totalObservations1 * (histogram1[i] + histogram2[i]) / (double) (totalObservations1 + totalObservations2);
            expectedFrequencies2[i] = (double) totalObservations2 * (histogram2[i] + histogram1[i]) / (double) (totalObservations1 + totalObservations2);
        }
        //Log.d("fffF", String.valueOf(chiSquare));

        double chiSquare = 0.0;

        for (int i = 0; i < histogram1.length; i++) {
            double diff1 = histogram1[i] - expectedFrequencies1[i];
            double diff2 = histogram2[i] - expectedFrequencies2[i];

            if (expectedFrequencies1[i] > 0)
                chiSquare += (diff1 * diff1) / expectedFrequencies1[i];
            if (expectedFrequencies2[i] > 0)
                chiSquare += (diff2 * diff2) / expectedFrequencies2[i];
        }

        TextView NTN = findViewById(R.id.NTN);

        NTN.setText(String.format("%.2f", chiSquare));
        ///Toast.makeText(this, "" + chiSquare, Toast.LENGTH_SHORT).show();
    }
}