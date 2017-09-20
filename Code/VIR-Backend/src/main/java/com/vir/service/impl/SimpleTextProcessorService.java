package com.vir.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vir.model.Text;
import com.vir.model.Word;
import com.vir.repository.WordRepository;
import com.vir.service.TextProcessorService;
import com.vir.service.WordService;

/**
 * A simple text processor to match a word to the category.
 * 
 * @author Alfredo Lopez
 *
 */
@Service("simpleTextProcessor")
public class SimpleTextProcessorService implements TextProcessorService {

	private static final Logger LOG = LoggerFactory.getLogger(SimpleTextProcessorService.class);

	@Autowired
	private WordRepository wordRepository;

	@Autowired
	@Qualifier("simpleWordService")
	private WordService wordService;

	@Override
	public Text process(String textString) {

		List<String> orgiginalStrings = Arrays.asList(textString.split(StringUtils.SPACE));
		Map<String, Word> map = new HashMap<>();
		List<Word> finalList = new ArrayList<>();

		Word result = null;
		for (String s : orgiginalStrings) {
			if (!map.containsKey(s)) {
				String cleanValue = wordService.removePunctuation(s).toLowerCase();
				result = wordRepository.findFirstByValue(cleanValue);
				
				if (result == null) {
					result = new Word(StringUtils.EMPTY, StringUtils.EMPTY, s);
				}
				map.put(s, result);
			} else {
				result = map.get(s);
			}
			finalList.add(result);
		}

		Text text = new Text();
		text.setWords(finalList);

		return text;
	}
}
