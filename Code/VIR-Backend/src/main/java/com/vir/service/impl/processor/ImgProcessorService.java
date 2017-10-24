package com.vir.service.impl.processor;

import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.opencv.core.Mat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vir.exception.UnparseableContentException;
import com.vir.helpers.IOHelper;
import com.vir.model.FileType;
import com.vir.model.Text;
import com.vir.service.FileProcessorService;
import com.vir.service.TextProcessorService;
import com.vir.service.impl.OcrOptimizerService;
import com.vir.service.impl.TesseractConfigurationService;

@Service("imgProcessorService")
public class ImgProcessorService implements FileProcessorService {

	@Autowired
	@Qualifier("optimizedTextProcessorService")
	private TextProcessorService textProcessorService;

	@Autowired
	private OcrOptimizerService ocrOptimizerService;

	@Autowired
	private TesseractConfigurationService tessConfiguration;

	@Override
	public Text process(MultipartFile file, FileType type) throws Exception {

		Parser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler();
		ParseContext parseContext = new ParseContext();
		parseContext.set(TesseractOCRConfig.class, tessConfiguration.getConfig());
	
		try (InputStream fileStream = file.getInputStream()) {
			
			Mat image = IOHelper.inputStreamToMat(fileStream);
			Mat optimizedImage = ocrOptimizerService.optimize(image);
			
			try (InputStream processedImage = IOHelper.matToInputStream(optimizedImage);
					TikaInputStream stream = TikaInputStream.get(processedImage);) {
				
				parser.parse(stream, handler, new Metadata(), parseContext);
				
				if (StringUtils.isEmpty(handler.toString().trim())) {
					throw new UnparseableContentException("Could not parse the file.");
				}
				
				return textProcessorService.process(handler.toString());
			} finally {
				// Make sure to close all matrices explicitly
				image.release();
				optimizedImage.release();
			}
		}
	}
}
