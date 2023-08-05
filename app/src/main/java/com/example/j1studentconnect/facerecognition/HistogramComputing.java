package com.example.j1studentconnect;

//import org.opencv.core.CvType.CV_32F;
//import org.opencv.core.CvType.CV_32FC3;
//import org.opencv.core.CvType.CV_32FC1;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.Toast;

import com.example.j1studentconnect.tabsinmain.MainActivity;

public class HistogramComputing {
    public int[] HistogramComputing(Bitmap bitmap){

        int[] histogram = calculateHistogram(bitmap);
        return histogram;
    }

    private int[] calculateHistogram(Bitmap bitmap) {
        int[] histogram = new int[256];

        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                int pixel = bitmap.getPixel(i, j);
                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);

                // Calculate the intensity of the pixel
                int intensity = (red + green + blue) / 3;

                histogram[intensity]++;
            }
        }

        return histogram;
    }

}

