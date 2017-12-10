package com.vir.service.impl.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vir.exception.UnsupportedFileException;
import com.vir.model.FileType;
import com.vir.model.Text;
import com.vir.service.FileProcessorService;

@Service("simpleFileProcessorService")
public class SimpleFileProcessorService implements FileProcessorService {
	

	@Autowired
	@Qualifier("pdfProcessorService")
	private FileProcessorService pdfProcessorService;
	
	@Autowired
	@Qualifier("docProcessorService")
	private FileProcessorService docProcessorService;
	
	@Autowired
	@Qualifier("imgProcessorService")
	private FileProcessorService imgProcessorService;


	@Override
	public Text process(MultipartFile file, FileType type) throws Exception {

		switch (type) {
		case PDF:
			return pdfProcessorService.process(file, type);
		case DOC:
			return docProcessorService.process(file, type);
		case IMG:
			return imgProcessorService.process(file, type);
		default:
			throw new UnsupportedFileException(type.toString());
		}
	}
}
