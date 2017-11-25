package com.vir.service.impl.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringRunner;

import com.vir.GeneralDevTest;
import com.vir.model.Text;
import com.vir.model.WordMatch;
import com.vir.service.TextProcessorService;

@RunWith(SpringRunner.class)
@GeneralDevTest
public class OptimizedTextProcessorServiceTest {

	@Autowired
	@Qualifier("optimizedTextProcessorService")
	TextProcessorService textProcessorService;

	@Test
	public void process_WordDogWithCapitalLettersAndADot_ReturnsOneValue() {
		final String word = "Dog.";
		final Text result = textProcessorService.process(word);
		assertFalse(result.getWords().isEmpty());
	}

	@Test
	public void process_WordDogWithCapitalLettersAndADot_ReturnsADogValue() {
		final String word = "Dog.";
		final Text result = textProcessorService.process(word);
		assertEquals("dog", result.getWords().get(0).getValue());
	}

	@Test
	public void process_DogWithDotTestGoodTest_ReturnsValidList() {
		final String word = "Dog. test good test";
		final Text result = textProcessorService.process(word);
		assertEquals("dog", result.getWords().get(0).getValue());
		assertEquals("test", result.getWords().get(1).getValue());
		assertEquals("good", result.getWords().get(2).getValue());
		assertEquals("test", result.getWords().get(3).getValue());
	}

	@Test
	public void process_UnknownWord_ReturnsWordWithoutData() {
		final String word = "zzzzzz";
		final WordMatch expected = new WordMatch(StringUtils.EMPTY, StringUtils.EMPTY, word);
		final Text result = textProcessorService.process(word);
		assertEquals(expected, result.getWords().get(0));
	}

	@Test
	public void countWords_WithTextWith2WordsWithNoSpace_Returns2() {
		List<WordMatch> words = Arrays.asList(new WordMatch("", "", "dog"), new WordMatch("", "", "pet"));
		assertEquals(2, textProcessorService.countWords(words));
	}

	@Test
	public void countWords_WithTextWith2WordsWithSpace_Returns2() {
		List<WordMatch> words = Arrays.asList(new WordMatch("", "", "dog"), new WordMatch("", "", " "),
				new WordMatch("", "", "pet"));
		assertEquals(2, textProcessorService.countWords(words));
	}

	@Test
	public void countWords_WithTextWith2WordsWithTab_Returns2() {
		List<WordMatch> words = Arrays.asList(new WordMatch("", "", "dog"), new WordMatch("", "", "\t"),
				new WordMatch("", "", "pet"));
		assertEquals(2, textProcessorService.countWords(words));
	}

	@Test
	public void countWords_WithTextWith1WordWith3Paces_Returns0() {
		List<WordMatch> words = Arrays.asList(new WordMatch("", "", "   "));
		assertEquals(0, textProcessorService.countWords(words));
	}

	@Test
	public void countSentences_WithTextWith1Dot1Question1Exclamation_Returns3() {
		final String sample = "This is a sentense. Hey! Is that interrogation?";
		assertEquals(3, textProcessorService.countSentences(sample));
	}

	@Test
	public void countSentences_WithTextWithInMiddle_Returns1() {
		final String sample = "Is 2.5 a sentence?";
		assertEquals(1, textProcessorService.countSentences(sample));
	}

	@Test
	public void getFleschReadingEase_WithDataFromWebSite_Returns60_20() {
		long wordCount = 300;
		long sentenceCount = 12;
		long syllableCount = 430;
		assertEquals(60.20d, textProcessorService.getFleschReadingEase(wordCount, sentenceCount, syllableCount), 0.001d);
	}
	
	@Test
	public void getFleschReadingEase_WithValueAbove100_Returns100() {
		long wordCount = 1000;
		long sentenceCount = 1000;
		long syllableCount = 1000;
		assertEquals(100.00, textProcessorService.getFleschReadingEase(wordCount, sentenceCount, syllableCount), 0.001d);
	}
	
	@Test
	public void getFleschReadingEase_WithValueBellow0_Returns0() {
		long wordCount = 1000;
		long sentenceCount = 10000;
		long syllableCount = 10000;
		assertEquals(0.00, textProcessorService.getFleschReadingEase(wordCount, sentenceCount, syllableCount), 0.001d);
	}
	
	@Test
	public void getFleschReadingEase_WithWordCountLessThan100_Returns0() {
		long wordCount = 1;
		long sentenceCount = 1;
		long syllableCount = 1;
		assertEquals(0.00, textProcessorService.getFleschReadingEase(wordCount, sentenceCount, syllableCount), 0.001d);
	}
}
