package com.vir.model.dictionary.oxford;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeadwordEntry {

	private String id;
	private String language;
	private List<LexicalEntry> lexicalEntries;
	private List<Pronunciation> pronunciations;
	private String type;
	private String word;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<LexicalEntry> getLexicalEntries() {
		return lexicalEntries;
	}

	public void setLexicalEntries(List<LexicalEntry> lexicalEntries) {
		this.lexicalEntries = lexicalEntries;
	}

	public List<Pronunciation> getPronunciations() {
		return pronunciations;
	}

	public void setPronunciations(List<Pronunciation> pronunciations) {
		this.pronunciations = pronunciations;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
