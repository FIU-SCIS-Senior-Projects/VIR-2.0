package com.vir.service.impl;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vir.model.FileType;
import com.vir.model.Text;
import com.vir.service.FileProcessorService;
import com.vir.service.TextProcessorService;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

@Service("imgProcessorService")
public class ImgProcessorService implements FileProcessorService {

	@Autowired
	@Qualifier("optimizedTextProcessorService")
	private TextProcessorService textProcessorService;

	@Value("${TESSDATA_PREFIX:./}")
	String tessData;

	@Override
	public Text process(MultipartFile file, FileType type) throws Exception {

		try {
			ITesseract instance = new Tesseract();
			instance.setDatapath(tessData);

			File convFile = File.createTempFile("temp", file.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();

			String result = instance.doOCR(convFile);
			return textProcessorService.process(result.toString());
		} catch (Exception e) {
			throw e;
		}
	}
}
