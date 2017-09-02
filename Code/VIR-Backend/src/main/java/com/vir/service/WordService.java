package com.vir.service;

import org.springframework.stereotype.Service;

@Service
public interface WordService {
	public String removePunctuation(String word);
}
