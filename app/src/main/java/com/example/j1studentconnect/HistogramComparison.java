//package com.example.j1studentconnect;
//
//import org.opencv.core.Core;
//import org.opencv.core.CvType;
//import org.opencv.core.Mat;
//import org.opencv.core.MatOfFloat;
//import org.opencv.core.MatOfInt;
//import org.opencv.core.Scalar;
//import org.opencv.core.Size;
//import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.imgproc.Imgproc;
//
//import java.util.Arrays;
////import org.opencv.core.CvType.CV_32F;
////import org.opencv.core.CvType.CV_32FC3;
////import org.opencv.core.CvType.CV_32FC1;
//
//public class HistogramComparison {
//
//            Mat src1 = Imgcodecs.imread("thay_trinh.jpg"); // Load the first image
//            Mat src2 = Imgcodecs.imread("thay"); // Load the second image
//
//            Mat hsvSrc1 = new Mat();
//            Mat hsvSrc2 = new Mat();
//
//            // Convert images to HSV color space
//            //Imgproc.cvtColor(src1, hsvSrc1, Imgproc.COLOR_BGR2HSV);
//            //Imgproc.cvtColor(src2, hsvSrc2, Imgproc.COLOR_BGR2HSV);
//
//            // Set histogram parameters
//            int hBins = 50; // Number of histogram bins for Hue component
//            int sBins = 60; // Number of histogram bins for Saturation component
//            int[] histSize = {hBins, sBins};
//
//            int histSizeTotal = hBins * sBins; // Total number of histogram bins
//            float[] ranges = {0, 180, 0, 256}; // Range for Hue and Saturation components
//            int[] channels = {0, 1}; // Compute histogram from both Hue and Saturation components
//
//            // Calculate histograms for both images
//            MatOfFloat histRanges = new MatOfFloat(ranges);
//            Mat histSrc1 = new Mat();
//            Mat histSrc2 = new Mat();
//
//            Imgproc.calcHist(
//                    Arrays.asList(hsvSrc1),
//                    new MatOfInt(channels),
//                    new Mat(),
//                    histSrc1,
//                    new MatOfInt(histSize),
//                    histRanges
//            );
//
//            Imgproc.calcHist(
//                    Arrays.asList(hsvSrc2),
//                    new MatOfInt(channels),
//                    new Mat(),
//                    histSrc2,
//                    new MatOfInt(histSize),
//                    histRanges
//            );
//
//            // Normalize histograms
//            Core.normalize(histSrc1, histSrc1, 0, 1, Core.NORM_MINMAX, -1, new Mat());
//            Core.normalize(histSrc2, histSrc2, 0, 1, Core.NORM_MINMAX, -1, new Mat());
//
//            // Compare histograms using histogram intersection method
//            double result = Imgproc.compareHist(histSrc1, histSrc2, Imgproc.COMPARE_INTERSECT);
//
//            //return result;
//}
//
