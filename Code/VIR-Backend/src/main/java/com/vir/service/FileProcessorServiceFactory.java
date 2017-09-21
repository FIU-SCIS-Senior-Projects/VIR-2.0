package com.vir.service;

import javax.validation.ConstraintViolationException;

import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.multipart.MultipartFile;

import com.vir.model.FileType;
import com.vir.service.impl.DocProcessorService;
import com.vir.service.impl.PdfProcessorService;
import com.vir.service.impl.PicProcessorService;

public class FileProcessorServiceFactory {

	public static FileProcessorService getInstance(MultipartFile file, FileType type) 
			throws HttpMediaTypeNotSupportedException {

		switch (type) {
		case PDF:
			return new PdfProcessorService(file);
		case DOC:
			return new DocProcessorService(file);
		case PIC:
			return new PicProcessorService(file);
		default:
			throw new HttpMediaTypeNotSupportedException(type.toString() + " is not a supported type.");
		}
	};
}
