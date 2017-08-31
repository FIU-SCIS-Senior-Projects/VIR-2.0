package com.vir.service;

import org.springframework.stereotype.Service;

import com.vir.model.Text;

@Service
public interface TextProcessorService {
	public Text process(String text);
}
