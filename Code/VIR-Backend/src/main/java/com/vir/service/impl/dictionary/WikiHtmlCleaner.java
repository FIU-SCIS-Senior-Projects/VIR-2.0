package com.vir.service.impl.dictionary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class WikiHtmlCleaner {

	/**
	 * Cleans the string to be returned.
	 * @param html the html string to clean.
	 * @return the clean string.
	 */
	public String clean(String html) {

		String result = html;
		result = extractEnglishOrGuess(result);
		result = replaceAllEmptyTagsWithNothing(result);
		return result.trim();
	}

	
	/**
	 * Removes all empty tags from the html.
	 * @param html the html to analyze.
	 * @return a string without empty tags.
	 */
	private String replaceAllEmptyTagsWithNothing(String html) {
		final String expression = "((?:<[^>\\/]+>)(?:|\\s+)(?:<\\/[^>]+>))";
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(html);

		if (matcher.find()) {
			html = matcher.replaceAll(" ");
			System.out.println(matcher.replaceAll(" "));
			return html;
		} else {
			return html;
		}
	}

	
	/**
	 * Extracts the English part of the text. If it cannot find the correct
	 * boundaries it returns the original.
	 * @param html the html to analyze.
	 * @return the result.
	 */
	private String extractEnglishOrGuess(String html) {

		final String expression = "(?:(?:<h2><span id=\"English\">English<\\/span><\\/h2>)(.*?)(?:<hr>))";
		Pattern pattern = Pattern.compile(expression, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(html);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return html;
		}
	}
}
