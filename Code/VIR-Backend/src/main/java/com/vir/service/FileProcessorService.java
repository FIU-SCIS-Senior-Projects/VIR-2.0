package com.vir.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vir.model.Text;

@Service
public interface FileProcessorService {
	public Text process(MultipartFile file);
}
