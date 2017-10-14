package com.vir.service.impl;

import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	@Autowired
	private OcrOptimizerService ocrOptimizerService;
	
	@Autowired
	private OcrOptimizerService ocrOptimizer;
	
	@Autowired
	private TesseractConfigurationService tessConfiguration;

	@Override
	public Text process(MultipartFile file, FileType type) throws Exception {

		Parser JpegParser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler();
		
		ParseContext parseContext = new ParseContext();
		parseContext.set(TesseractOCRConfig.class, tessConfiguration.getConfig());

		InputStream deSkewedInputStream = ocrOptimizerService.deskew(file.getInputStream());
		InputStream bwInputStream = ocrOptimizerService.toGreyScale(deSkewedInputStream);
		InputStream biInputStream =  ocrOptimizer.binarize(bwInputStream);

		JpegParser.parse(biInputStream, handler, new Metadata(), parseContext);

		if (StringUtils.isEmpty(handler.toString().trim())) {
			return new Text();
		}

		return textProcessorService.process(handler.toString());
	}
}
