package com.vir.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.vir.model.Word;

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
