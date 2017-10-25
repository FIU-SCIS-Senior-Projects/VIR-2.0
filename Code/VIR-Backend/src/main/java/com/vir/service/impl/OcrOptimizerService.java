package com.vir.service.impl;

import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import static org.opencv.imgproc.Imgproc.CV_WARP_FILL_OUTLIERS;
import static org.opencv.imgproc.Imgproc.GaussianBlur;
import static org.opencv.imgproc.Imgproc.INTER_LINEAR;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY;
import static org.opencv.imgproc.Imgproc.THRESH_OTSU;

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

import com.vir.helpers.IOHelper;

import net.sourceforge.tess4j.util.ImageHelper;
import nu.pattern.OpenCV;

@Service
public class OcrOptimizerService {

	/**
	 * Load the library once. We use the constructor and Autowiring to make sure we
	 * have this in our class path.
	 */
	public OcrOptimizerService() {
		OpenCV.loadShared();
	}

	public Mat optimize(Mat image) throws Exception {

		Mat result;
		result = deskew(image);
		result = convertToGreyScaleOpencv(image);
		result = threshold(result);
		
		return result;
	}

	public Mat deskew(Mat image) {

		Mat result = new Mat();

		convertToGreyScaleOpencv(image);
		Core.bitwise_not(image, result);
		Imgproc.threshold(result, result, 0, 255, THRESH_BINARY + THRESH_OTSU);

		// Get the deskewed angle and rotate.
		double angle = getAngle(result);

		// Do the rotation transformation.
		Point center = new Point(result.width() / 2, result.height() / 2);
		Mat rotImage = Imgproc.getRotationMatrix2D(center, angle, 1.0);
		Size size = new Size(result.width(), result.height());
		Imgproc.warpAffine(result, result, rotImage, size, INTER_LINEAR + CV_WARP_FILL_OUTLIERS);

		// Revert the inversion.
		Core.bitwise_not(result, result);

		return result;
	}

	/**
	 * Converts to a grey scale.
	 * 
	 * @param matrix the matrix to convert.
	 * @return the converted matrix.
	 */
	public Mat convertToGreyScaleOpencv(Mat matrix) {

		// Only convert to grey is the image has more than 1 channel.
		if (matrix.channels() > 1) {
			Imgproc.cvtColor(matrix, matrix, COLOR_BGR2GRAY);
		}

		return matrix;
	}

	
	/**
	 * Converts to a grey scale using Tesseract.
	 * 
	 * @param matrix the matrix to convert.
	 * @return the converted matrix.
	 * @throws Exception 
	 */
	public Mat convertToGreyScaleTess(Mat matrix) throws Exception {
		BufferedImage grey = ImageHelper.convertImageToGrayscale(IOHelper.matToBufferedImage(matrix));
		return IOHelper.bufferedImageToMat(grey);
	}
	
	public Mat threshold( Mat matrix) {
		matrix = convertToGreyScaleOpencv(matrix);
		GaussianBlur(matrix, matrix, new Size(5, 5), 0);
		Imgproc.adaptiveThreshold(matrix, matrix, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, 18);
		return matrix;
	}
	
	
	/**
	 * Helper method to determine the angle of skewness in a picture.
	 * 
	 * @param matrix the matrix to check.
	 * @return the angle calculated.
	 */
	protected double getAngle(Mat matrix) {

		Size size = matrix.size();
		Mat lines = new Mat();
		Imgproc.HoughLinesP(matrix, lines, 1, Math.PI / 180, 100, size.width / 2.f, 20);
		double angle = 0.;
		for (int i = 0; i < lines.height(); i++) {
			for (int j = 0; j < lines.width(); j++) {
				angle += Math.atan2(lines.get(i, j)[3] - lines.get(i, j)[1], lines.get(i, j)[2] - lines.get(i, j)[0]);
			}
		}
		angle /= lines.size().area();
		angle = angle * 180 / Math.PI;
		return angle;
	}
}