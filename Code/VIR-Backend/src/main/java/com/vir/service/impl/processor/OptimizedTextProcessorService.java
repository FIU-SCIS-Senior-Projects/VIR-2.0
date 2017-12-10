package com.vir.service.impl.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vir.model.Count;
import com.vir.model.Percent;
import com.vir.model.Statistics;
import com.vir.model.Text;
import com.vir.model.Word;
import com.vir.model.WordMatch;
import com.vir.repository.WordRepository;
import com.vir.service.TextProcessorService;
import com.vir.service.WordService;

/**
 * A simple text processor to match a word to the category.
 * 
 * @author Alfredo Lopez
 *
 */
@Service("optimizedTextProcessorService")
@Transactional
public class OptimizedTextProcessorService implements TextProcessorService {

	@Autowired
	private WordRepository wordRepository;

	@Autowired
	@Qualifier("simpleWordService")
	private WordService wordService;

	@Override
	public Text process(String textString) {

		// @formatter:off
		List<String> originalWords = getStrings(textString);
		List<String> cleanValues = originalWords.stream()
									.map(wordService::clean)
									.filter(StringUtils::isNotBlank)
									.collect(Collectors.toList());
		List<Word> resultWords = wordRepository.findAllIn(cleanValues);
		
		Map<String, Word> resultsMap = resultWords.stream().collect(Collectors.toMap(Word::getValue, w -> w));
		List<WordMatch> matches = new ArrayList<>();

		// Loop through the word list and clean it
		for (String originalWord : originalWords) {

			String clean = wordService.clean(originalWord);
			WordMatch match = new WordMatch(originalWord);

			// if result map contains the word then grab it.
			if (resultsMap.containsKey(clean)) {
				Word w = resultsMap.get(clean);
				match.setCategory(w.getCategory());
				match.setValue(w.getValue());
			}
			matches.add(match);
		}

		// Collect all data
		Count wordCount = getWordCount(matches);
		Percent wordPercent = new Percent(wordCount);
		Statistics statistics = new Statistics(wordCount, wordPercent);
		Long sentenceCount = countSentences(textString);
		Double fleschReadingScore = getFleschReadingEase(countWords(matches), sentenceCount, getSyllableCount(matches));

		return new Text(matches, fleschReadingScore, statistics, sentenceCount);
		// @formatter:on
	}

	/**
	 * Get the count of all the words in the test.
	 * 
	 * @param text the test to count.
	 * @return an instance of the count.
	 */
	private Count getWordCount(List<WordMatch> words) {
		long awl = 0;
		long hi = 0;
		long low = 0;
		long med = 0;
		long noCategory = 0;

		for (WordMatch wordMatch : words) {
			if (!StringUtils.isBlank(wordMatch.getInitialValue())) {
				awl += wordMatch.getCategory().equalsIgnoreCase("awl") ? 1 : 0;
				hi += wordMatch.getCategory().equalsIgnoreCase("hi") ? 1 : 0;
				med += wordMatch.getCategory().equalsIgnoreCase("med") ? 1 : 0;
				low += wordMatch.getCategory().equalsIgnoreCase("low") ? 1 : 0;

				if (StringUtils.isBlank(wordMatch.getCategory())) {
					noCategory++;
				}
			}
		}
		return new Count(awl, hi, med, low, noCategory);
	}

	/**
	 * Gets the list of strings from a text.
	 * 
	 * @param textString the string to split
	 * @return A list of strings
	 * 
	 *         Note: We make sure there is no more than two white spaces between the
	 *         words.
	 */
	private List<String> getStrings(String textString) {
		final String regex = "[\\n\\r\\s]";
		textString = textString.trim();
		return Arrays.asList(textString.split(regex));
	}

	/**
	 * Get's the counts of the words from a text.
	 */
	@Override
	public long countWords(List<WordMatch> words) {
		return words.stream().filter(isValidWord()).count();
	}

	/**
	 * Counts the amount of sentences in a text.
	 * 
	 * We are taking into consideration the decimal numbers.
	 */
	@Override
	public long countSentences(String textString) {
		final String regex = "([a-zA-Z\\s](?:[\\.?!]){1,3}[\\s])";
		textString = textString.trim();
		List<String> sentences = Arrays.asList(textString.split(regex));
		return sentences.size();
	}

	/**
	 * Calculation for the FleschReadingEase
	 * 
	 * http://www.readabilityformulas.com/flesch-reading-ease-readability-formula.php
	 */
	@Override
	public double getFleschReadingEase(long wordCount, long sentenceCount, long syllableCount) {

		final double maxThreshold = 100.0d;
		final double minThreshold = 0.0d;
		final int minWordCount = 100;
		final double baseConstant = 206.835;
		final double sentenceLengthRatio = 1.015;
		final double syllableRatio = 84.6;
		final double ASL = ((double) wordCount / sentenceCount);
		final double ASW = ((double) syllableCount / wordCount);
		
		if (wordCount < minWordCount) {
			return minThreshold;
		}

		double result = (baseConstant - (sentenceLengthRatio * ASL) - (syllableRatio * ASW));
		
		if (result > maxThreshold) {
			return maxThreshold;
		}
		
		if (result < minThreshold) {
			return minThreshold;
		}
		
		return result;
	}

	/**
	 * Predicate to determine what is a valid word.
	 * 
	 * @return True if is a valid word, else false
	 */
	private static Predicate<WordMatch> isValidWord() {
		return w -> {
			String value = w.getInitialValue().trim();

			if (value.length() > 1) {
				return true;
			}

			if ((value.length() == 1) && Character.isLetterOrDigit(w.getInitialValue().toCharArray()[0])) {
				return true;
			}
			return false;
		};
	}

	/**
	 * Gets a total of all the syllables in a text.
	 * 
	 * @param text the text to be inspected.
	 * @return the count.
	 */
	private long getSyllableCount(List<WordMatch> words) {

		long total = 0;
		for (WordMatch wordMatch : words) {
			if (StringUtils.isNoneBlank(wordMatch.getInitialValue())) {
				total += wordService.countSyllables(wordMatch.getInitialValue());
			}
		}
		return total;
	}
}
