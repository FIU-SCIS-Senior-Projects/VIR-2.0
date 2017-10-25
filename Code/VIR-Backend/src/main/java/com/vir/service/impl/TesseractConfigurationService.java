package com.vir.service.impl;

import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vir.config.TesseractPropertiesConfig.TesseractProperties;

@Service
public class TesseractConfigurationService {
	
	@Autowired
	private TesseractProperties tesseractProperties;

	public TesseractOCRConfig getConfig() {
		TesseractOCRConfig config = new TesseractOCRConfig();
		config.setTessdataPath(tesseractProperties.tessdataPrefix);
		config.setTesseractPath(tesseractProperties.tesseractPath);
		config.setEnableImageProcessing(1);
		return config;
	}
}
