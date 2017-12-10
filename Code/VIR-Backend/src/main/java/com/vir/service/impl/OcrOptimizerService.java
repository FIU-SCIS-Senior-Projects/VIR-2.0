package com.vir.service.impl;

import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import static org.opencv.imgproc.Imgproc.GaussianBlur;

import java.awt.image.BufferedImage;

import org.opencv.core.Mat;
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
		result = convertToGreyScaleOpencv(image);
		result = threshold(result);
		return result;
	}

	/**
	 * Converts to a grey scale.
	 * 
	 * Only convert to grey is the image has more than 1 channel.
	 * 
	 * @param matrix the matrix to convert.
	 * @return the converted matrix.
	 */
	public Mat convertToGreyScaleOpencv(Mat matrix) {
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

	public Mat threshold(Mat matrix) {
		matrix = convertToGreyScaleOpencv(matrix);
		GaussianBlur(matrix, matrix, new Size(5, 5), 0);
		Imgproc.adaptiveThreshold(matrix, matrix, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, 18);
		return matrix;
	}
}