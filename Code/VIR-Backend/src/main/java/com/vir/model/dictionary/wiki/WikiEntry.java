package com.vir.model.dictionary.wiki;

import com.vir.model.DictionaryEntry;

public class WikiEntry implements DictionaryEntry{
	
	private String html;

	public WikiEntry(String html) {
		super();
		this.html = html;
	}
	
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
}
