package com.vir.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vir.model.dictionary.oxford.RetrieveEntry;
import com.vir.model.dictionary.wiki.WikiEntry;

/**
 * Class to hole the definitons data.
 * 
 * @author Alfredo Lopez
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dictionary {

	private RetrieveEntry oxford;
	private WikiEntry wiki;
	private DictionaryEntry wordAPI;
	private DictionaryEntry merriam;
	
	public RetrieveEntry getOxford() {
		return oxford;
	}
	public void setOxford(RetrieveEntry oxford) {
		this.oxford = oxford;
	}
	public WikiEntry getWiki() {
		return wiki;
	}
	public void setWiki(WikiEntry wiki) {
		this.wiki = wiki;
	}
	public DictionaryEntry getWordAPI() {
		return wordAPI;
	}
	public void setWordAPI(DictionaryEntry wordAPI) {
		this.wordAPI = wordAPI;
	}
	public DictionaryEntry getMerriam() {
		return merriam;
	}
	public void setMerriam(DictionaryEntry merriam) {
		this.merriam = merriam;
	}	
}
