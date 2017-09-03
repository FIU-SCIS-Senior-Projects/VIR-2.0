package com.vir.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vir.service.WordService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleWordServiceTest {

	@Autowired
	@Qualifier("simpleWordService")
	private WordService wordService;

	@Test
	public void removePunctuation_WithADot_ReturnsWordWithoutDot() throws Exception {
		final String word = "word.";
		final String expected = "word";
		assertEquals(expected, wordService.removePunctuation(word));
	}

	@Test
	public void getSingular_WordEndingInS_ReturnsWordWithoutS() throws Exception {
		final String word = "dogs";
		final String expected = "dog";
		assertEquals(expected, wordService.getSingular(word));
	}
	
	@Test
	public void getSingular_WordEndingInIES_ReturnsWordEndingInY() throws Exception {
		final String word = "pennies";
		final String expected = "penny";
		assertEquals(expected, wordService.getSingular(word));
	}
	
	@Test
	public void getSingular_WordChindren_ReturnsChild() throws Exception {
		final String word = "children";
		final String expected = "child";
		assertEquals(expected, wordService.getSingular(word));
	}
	
	@Test
	public void getSingular_WordDeer_ReturnsDeer() throws Exception {
		final String word = "deer";
		final String expected = "deer";
		assertEquals(expected, wordService.getSingular(word));
	}
}
