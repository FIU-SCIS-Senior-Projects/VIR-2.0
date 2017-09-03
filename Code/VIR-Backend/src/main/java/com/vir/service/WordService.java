package com.vir.service;

import org.springframework.stereotype.Service;

/**
 * Main service used when processing a word.
 * 
 * @author Alfredo Lopez
 *
 */
@Service
public interface WordService {
	public String removePunctuation(String word);
}
