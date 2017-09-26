package com.vir.service.impl;

import java.io.InputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vir.model.FileType;
import com.vir.model.Text;
import com.vir.service.FileProcessorService;
import com.vir.service.TextProcessorService;

@Service("pdfProcessorService")
public class PdfProcessorService implements FileProcessorService {

	@Autowired
	@Qualifier("simpleTextProcessor")
	private TextProcessorService textProcessorService;

	@Override
	public Text process(MultipartFile file, FileType type) throws Exception {

		Parser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);

		TesseractOCRConfig config = new TesseractOCRConfig();
		PDFParserConfig pdfConfig = new PDFParserConfig();
		pdfConfig.setExtractInlineImages(true);

		ParseContext parseContext = new ParseContext();
		parseContext.set(TesseractOCRConfig.class, config);
		parseContext.set(PDFParserConfig.class, pdfConfig);
		parseContext.set(Parser.class, parser); // need to add this to make sure recursive parsing happens!

		InputStream stream = file.getInputStream();
		parser.parse(stream, handler, new Metadata(), parseContext);

		return textProcessorService.process(handler.toString());
	}

}
