package com.vir.service.impl;

import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY;
import static org.opencv.imgproc.Imgproc.THRESH_OTSU;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

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

	public Mat optimize(Mat image) {
		return deskew(image);
	}

	
	public Mat deskew(Mat image) {

		Mat result = new Mat();
		
		convertToGreyScale(image);
		Core.bitwise_not(image, result);
		Imgproc.threshold(result, result, 0, 255, THRESH_BINARY + THRESH_OTSU);

		// Get the deskewed angle and rotate.
		double angle = getAngle(result);
		
		// Do the rotation transformation.
		Point center = new Point(result.width() / 2, result.height() / 2);
		Mat rotImage = Imgproc.getRotationMatrix2D(center, angle, 1.0);
		Size size = new Size(result.width(), result.height());
		Imgproc.warpAffine(result, result, rotImage, size, Imgproc.INTER_LINEAR + Imgproc.CV_WARP_FILL_OUTLIERS);
		
		//Revert the inversion.
		Core.bitwise_not(result, result);
		
		return result;	
	}

	
	/**
	 * Converts to a grey scale.
	 * @param matrix the matrix to convert.
	 * @return the converted matrix.
	 */
	public Mat convertToGreyScale(Mat matrix) {
		
		// Only convert to grey is the image has more than 1 channel.
		if (matrix.channels() > 1) {
			Imgproc.cvtColor(matrix, matrix, COLOR_BGR2GRAY);
		} 
		
		return matrix;
	}
	
	/**
	 * Helper method to determine the angle of skewness in a picture.
	 * @param matrix	 the matrix to check. 
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

	// public InputStream deskew(InputStream fileInputStream) throws Exception {
	//
	// BufferedImage bi = ImageIO.read(fileInputStream);
	// ImageDeskew id = new ImageDeskew(bi);
	// double imageSkewAngle = id.getSkewAngle();
	// double MINIMUM_DESKEW_THRESHOLD = 0.05d;
	//
	// if ((imageSkewAngle > MINIMUM_DESKEW_THRESHOLD || imageSkewAngle <
	// -(MINIMUM_DESKEW_THRESHOLD))) {
	// bi = ImageHelper.rotateImage(bi, -imageSkewAngle);
	// }
	//
	// return IOHelper.bufferedImageToInputStream(bi);
	// }

	// public InputStream toGreyScale(InputStream fileInputStream) throws Exception
	// {
	// BufferedImage bi = ImageIO.read(fileInputStream);
	// BufferedImage bibw = ImageHelper.convertImageToGrayscale(bi);
	// return IOHelper.bufferedImageToInputStream(bibw);
	// }

	// public InputStream binarize(InputStream fileInputStream) throws Exception {
	// OpenCV.loadShared();
	// byte[] bytes = IOUtils.toByteArray(fileInputStream);
	// Mat gray = Imgcodecs.imdecode(new MatOfByte(bytes), CV_LOAD_IMAGE_GRAYSCALE);
	// Mat gray_with_gauss = new Mat();
	// Mat finalResult = new Mat();
	//
	// GaussianBlur(gray, gray_with_gauss, new Size(5, 5), 0);
	// Imgproc.threshold(gray_with_gauss, finalResult, 0, 255, THRESH_BINARY +
	// THRESH_OTSU);
	//
	// return IOHelper.Mat2InputStream(finalResult);
	// }
}
