package com.vir.service.impl;

import org.springframework.stereotype.Service;

import com.vir.service.WordService;

@Service("simpleWordService")
public class SimpleWordService implements WordService {

	@Override
	public String removePunctuation(String word) {
		return word.replaceAll("([~`!@#$%^&*;:\",.])", "");
	}

}
