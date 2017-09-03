package com.vir.model;

import java.util.ArrayList;
import java.util.List;

import com.vir.model.dto.WordDto;

public class Text {

	private List<WordDto> words = new ArrayList<>();
	
	public Text() {
	}

	public List<WordDto> getWords() {
		return words;
	}

	public void setWords(List<WordDto> words) {
		this.words = words;
	}
}
