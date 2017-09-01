package com.vir.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.vir.model.Word;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application.yml")
public class WordRepositoryTest {
	
	@Autowired
	private WordRepository wordRepository;

	@Test
	public void findAllIn_With2ExistingWords_ReturnsAListWith2Words() {
		List<String> strings = Arrays.asList("abandon", "abnormal");
		List<Word> words = wordRepository.findAllIn(strings);
		assertEquals(2, words.size());
	}
	
	@Test
	public void findAllIn_WithStringDog_ReturnsAWordWith() {
		List<String> strings = Arrays.asList("abandon");
		List<Word> words = wordRepository.findAllIn(strings);
		assertEquals("awl", words.get(0).getCategory());
	}
}
