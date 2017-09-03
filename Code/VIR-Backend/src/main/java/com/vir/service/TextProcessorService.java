package com.vir.service;

import org.springframework.stereotype.Service;

import com.vir.model.Text;

/**
 * Main service used when processing text.
 * 
 * @author Alfredo Lopez
 *
 */
@Service
public interface TextProcessorService {
	public Text process(String text);
}
