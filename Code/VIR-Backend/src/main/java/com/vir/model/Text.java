package com.vir.model;

import java.util.ArrayList;
import java.util.List;

public class Text {

	private List<Word> words = new ArrayList<>();
	
	public Text() {
	}

	public List<Word> getWords() {
		return words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}
}
