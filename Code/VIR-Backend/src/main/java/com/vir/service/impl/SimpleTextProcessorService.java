package com.vir.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vir.model.Text;
import com.vir.model.Word;
import com.vir.model.dto.WordDto;
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

	@Autowired
	private WordRepository wordRepository;

	@Autowired
	@Qualifier("simpleWordService")
	private WordService wordService;

	@Override
	public Text process(String textString) {

		List<String> orgiginalStrings = Arrays.asList(textString.split(StringUtils.SPACE));
		Map<String, String> map = new HashMap<>();
		List<WordDto> finalList = new ArrayList<>();

		for (String s : orgiginalStrings) {
			String cleanValue = wordService.removePunctuation(s).toLowerCase();
			Word result = wordRepository.findMax1ByValue(cleanValue);
			map.putIfAbsent(s, result == null ? null : result.getValue());

			// Collect the values
			if (result == null) {
				finalList.add(new WordDto(StringUtils.EMPTY, StringUtils.EMPTY, s));
			} else {
				finalList.add(new WordDto(result.getValue(), result.getCategory(), s));
			}
		}

		Text text = new Text();
		text.setWords(finalList);

		return text;
	}
}
