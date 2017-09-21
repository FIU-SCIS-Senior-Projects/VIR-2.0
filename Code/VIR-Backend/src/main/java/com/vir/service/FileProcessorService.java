package com.vir.service;

import org.springframework.stereotype.Service;

import com.vir.model.Text;

/**
 * Main service used when processing a file.
 * 
 * @author Alfredo Lopez
 *
 */
@Service
public interface FileProcessorService {
	public Text process();
	public void validate();
}
