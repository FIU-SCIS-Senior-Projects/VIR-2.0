package com.vir.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vir.service.WordService;

import net.thucydides.core.util.Inflector;

@Service("simpleWordService")
public class SimpleWordService implements WordService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SimpleWordService.class);

	@Override
	public String removePunctuation(String word) {
		return word.replaceAll("([~`!@#$%^&*;:\",.])", "");
	}

	@Override
	public String getSingular(String word) {
		return Inflector.getInstance().singularize(word);
	}

}
