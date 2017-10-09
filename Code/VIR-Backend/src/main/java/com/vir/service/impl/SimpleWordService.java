package com.vir.service.impl;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;

import com.vir.service.WordService;

import net.thucydides.core.util.Inflector;

@Service("simpleWordService")
public class SimpleWordService implements WordService {

	@Override
	public String removePunctuation(String word) {
		
		Character[] chars = ArrayUtils.toObject(word.toCharArray());
		StringBuilder stringBuilder = new StringBuilder();
			
		for (int i = 0; i < chars.length; i++) {
			if (Character.isLetterOrDigit(chars[i])) {
				stringBuilder.append(chars[i]);
			}
		}
		return stringBuilder.toString();
	}

	@Override
	public String getSingular(String word) {
		final int minPluralLength = 4;
		if (word.length() > minPluralLength) {
			return Inflector.getInstance().singularize(word);
		}
		return word;
	}

	@Override
	public String clean(String word) {
		return removePunctuation(getSingular(word)).toLowerCase();
	}

}
