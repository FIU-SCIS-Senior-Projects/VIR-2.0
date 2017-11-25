package com.vir.service.impl.dictionary;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.vir.GeneralDevTest;

@RunWith(SpringRunner.class)
@GeneralDevTest
public class WikiHtmlCleanerServiceTest {

	final static String THIS_IS_TEXT = "<p>This is text</p>";
	final static String ENGLISH_HEADER = "<h2><span id=\"English\">English</span></h2>";
	final static String HORIZONTAL_ROW_LINE_TAG = "<hr>";

	@Autowired
	private WikiHtmlCleanerService wikiHtmlCleanerService;

	@Test
	public void clean_withEmptyTags_returnsStringWithoutEmptyTags() throws Exception {
		final String html = "<p></p><h2></h2>" + THIS_IS_TEXT;
		assertEquals(THIS_IS_TEXT, wikiHtmlCleanerService.clean(html));
	}

	@Test
	public void clean_withEnglishHeader_returnsStringWithoutEnglishHeader() throws Exception {
		final String html = ENGLISH_HEADER + THIS_IS_TEXT;
		assertEquals(THIS_IS_TEXT, wikiHtmlCleanerService.clean(html));
	}

	@Test
	public void clean_withEnglishHeaderAndEndingTag_returnsStringWithTheContentsBetween() throws Exception {
		final String html = ENGLISH_HEADER + THIS_IS_TEXT + HORIZONTAL_ROW_LINE_TAG;
		assertEquals(THIS_IS_TEXT, wikiHtmlCleanerService.clean(html));
	}

	@Test
	public void clean_withoutEnglishHeaderOrEndingTag_returnsFullString() throws Exception {
		final String html = THIS_IS_TEXT;
		assertEquals(THIS_IS_TEXT, wikiHtmlCleanerService.clean(html));
	}
}
