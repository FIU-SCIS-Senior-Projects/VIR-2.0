//package com.vir.service.impl;
//
//import static org.junit.Assert.assertEquals;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.opencv.core.Mat;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.vir.helpers.IOHelper;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class OcrOptimizerServiceTest {
//	
//	@Autowired
//	private OcrOptimizerService ocrOptimizerService;
//
//	@Test
//	public void deskew_withASkewedImage_returnsADeskewedImage() throws Exception {
//		
//		final String filePath= "ocr_optimizer/skewed_image.jpg";
//		InputStream stream = new ClassPathResource(filePath).getInputStream();	
//		Mat image = IOHelper.inputStreamToMat(stream);
//		Mat deskewedImage = ocrOptimizerService.deskew(image);
//		
//		Mat reDeskewedImage = ocrOptimizerService.deskew(deskewedImage);
//		assertEquals(0.0d, ocrOptimizerService.getAngle(reDeskewedImage), 0.2d);
//	}
//	
//	@Test
//	public void convertToGreyScale_withAColorImage_returnsImageInGreyScale() throws Exception {
//		
//		final String filePath= "ocr_optimizer/color_image.jpg";
//		InputStream stream = new ClassPathResource(filePath).getInputStream();
//		Mat image = IOHelper.inputStreamToMat(stream);
//		
//		Mat grey = ocrOptimizerService.convertToGreyScaleTess(image);
//		assertEquals(1, grey.channels());
//	}
//	
//	@Test
//	public void cropBorders_withImage_returnsImageWithBordersCropped() throws Exception {
//		
//		final String filePath= "/Users/alfredo/Downloads/sample-pictures/a_christmas_carol_by_charles_dickens_segment.png";
//		InputStream stream = new FileInputStream(new File(filePath));
//		Mat image = IOHelper.inputStreamToMat(stream);
//		
//		Mat cropped = ocrOptimizerService.threshold(image);
//		
//		InputStream is = IOHelper.matToInputStream(cropped);
//		IOHelper.writeToPath("/Users/alfredo/Downloads/cropped.jpg", is);
//		
//	}
//}
