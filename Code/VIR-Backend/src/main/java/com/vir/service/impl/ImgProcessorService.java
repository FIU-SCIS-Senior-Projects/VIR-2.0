package com.vir.service.impl;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vir.model.FileType;
import com.vir.model.Text;
import com.vir.service.FileProcessorService;
import com.vir.service.TextProcessorService;

@Service("imgProcessorService")
public class ImgProcessorService implements FileProcessorService {

	@Autowired
	@Qualifier("optimizedTextProcessorService")
	private TextProcessorService textProcessorService;

	@Value("${TESSDATA_PREFIX}")
	String tessdataPath;

	@Value("${TESSERACT_PATH}")
	String tesseractPath;

	@Override
	public Text process(MultipartFile file, FileType type) throws Exception {

		Parser JpegParser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler();

		TesseractOCRConfig config = new TesseractOCRConfig();
		config.setTessdataPath(tessdataPath);
		config.setTesseractPath(tesseractPath);

		ParseContext parseContext = new ParseContext();
		parseContext.set(TesseractOCRConfig.class, config);
		
		JpegParser.parse(file.getInputStream(), handler, new Metadata(), parseContext);
		
		if (handler.toString().equals("")) {
			return new Text();
		}
		
		return textProcessorService.process(handler.toString());
	}
}
