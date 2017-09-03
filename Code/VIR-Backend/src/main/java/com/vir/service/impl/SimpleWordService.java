package com.vir.service.impl;

import org.springframework.stereotype.Service;

import com.vir.service.WordService;

import net.thucydides.core.util.Inflector;

@Service("simpleWordService")
public class SimpleWordService implements WordService {

	@Override
	public String removePunctuation(String word) {
		return word.replaceAll("([~`!@#$%^&*;:\",.])", "");
	}

	@Override
	public String getSingular(String word) {
		return Inflector.getInstance().singularize(word);
	}

}
