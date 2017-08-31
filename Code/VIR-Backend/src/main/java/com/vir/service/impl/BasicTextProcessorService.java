package com.vir.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vir.model.Text;
import com.vir.repository.WordRepository;
import com.vir.service.TextProcessorService;

@Service
public class BasicTextProcessorService implements TextProcessorService {

	@Autowired
	private WordRepository wordRepository;

	@Override
	public Text process(String text) {

		List<String> strings = Arrays.asList(text.split(StringUtils.SPACE));
		Text t = new Text();
		t.setWords(wordRepository.findAllIn(strings));

		return t;
	}
}
