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

	private List<Word> words;
	
	public Text() {
		this.words = new ArrayList<>();
	}

	public List<Word> getWords() {
		return words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}
}
