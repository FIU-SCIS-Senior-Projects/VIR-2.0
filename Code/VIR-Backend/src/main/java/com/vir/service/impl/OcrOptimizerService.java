package com.vir.service.impl;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.opencv.imgproc.Imgproc.GaussianBlur;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY;
import static org.opencv.imgproc.Imgproc.THRESH_OTSU;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

import com.recognition.software.jdeskew.ImageDeskew;
import com.vir.helpers.IOHelper;

import net.sourceforge.tess4j.util.ImageHelper;
import nu.pattern.OpenCV;

@Service
public class OcrOptimizerService {

	public InputStream deskew(InputStream fileInputStream) throws Exception {

		BufferedImage bi = ImageIO.read(fileInputStream);
		ImageDeskew id = new ImageDeskew(bi);
		double imageSkewAngle = id.getSkewAngle();
		double MINIMUM_DESKEW_THRESHOLD = 0.05d;

		if ((imageSkewAngle > MINIMUM_DESKEW_THRESHOLD || imageSkewAngle < -(MINIMUM_DESKEW_THRESHOLD))) {
			bi = ImageHelper.rotateImage(bi, -imageSkewAngle);
		}
		
		return IOHelper.bufferedImageToInputStream(bi);
	}

	public InputStream toGreyScale(InputStream fileInputStream) throws Exception {
		BufferedImage bi = ImageIO.read(fileInputStream);
		BufferedImage bibw = ImageHelper.convertImageToGrayscale(bi);
		return IOHelper.bufferedImageToInputStream(bibw);
	}

	public InputStream binarize(InputStream fileInputStream) throws Exception {
		OpenCV.loadShared();
		byte[] bytes = IOUtils.toByteArray(fileInputStream);
		Mat gray = Imgcodecs.imdecode(new MatOfByte(bytes), CV_LOAD_IMAGE_GRAYSCALE);
		Mat gray_with_gauss = new Mat();
		Mat finalResult = new Mat();

		GaussianBlur(gray, gray_with_gauss, new Size(5, 5), 0);
		Imgproc.threshold(gray_with_gauss, finalResult, 0, 255, THRESH_BINARY + THRESH_OTSU);

		return IOHelper.Mat2InputStream(finalResult);
	}
}
