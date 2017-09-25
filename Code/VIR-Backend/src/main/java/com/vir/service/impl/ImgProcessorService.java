package com.vir.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vir.model.FileType;
import com.vir.model.Text;
import com.vir.service.FileProcessorService;

@Service("imgProcessorService")
public class ImgProcessorService implements FileProcessorService {

	@Override
	public Text process(MultipartFile file, FileType type) {
		// TODO Auto-generated method stub
		return null;
	}
}
