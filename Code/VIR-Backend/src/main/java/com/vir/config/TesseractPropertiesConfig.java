package com.vir.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TesseractPropertiesConfig {
	
	@Value("${TESSDATA_PREFIX}")
	private String tessdataPrefix;

	@Value("${TESSERACT_PATH}")
	private String tesseractPath;

	@Value("${IMAGE_MAGICK_PATH}")
	private String imageMagickPath;
	
	@Bean
	TesseractProperties ocrConfig() {
		TesseractProperties properties = new TesseractProperties();
		properties.tessdataPrefix = this.tessdataPrefix;
		properties.tesseractPath = this.tesseractPath;
		properties.imageMagickPath = this.imageMagickPath;
		return properties;
	}
	
	public static class TesseractProperties {
		public String tessdataPrefix;
		public String tesseractPath;
		public String imageMagickPath;
	}
}
