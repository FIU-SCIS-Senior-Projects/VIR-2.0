package com.vir.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to hold the list of words. In a way an abstract 
 * representation of a text fragment.
 * 
 * @author Alfredo Lopez
 *
 */
public class Text {

	private List<WordMatch> words;
	
	public Text() {
		this.words = new ArrayList<>();
	}

	public List<WordMatch> getWords() {
		return words;
	}

	public void setWords(List<WordMatch> words) {
		this.words = words;
	}
}
