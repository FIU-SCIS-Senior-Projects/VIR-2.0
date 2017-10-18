package com.vir.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vir.model.dictionary.oxford.RetrieveEntry;

/**
 * Class to hole the definitons data.
 * 
 * @author Alfredo Lopez
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dictionary {

	private RetrieveEntry oxford;
	private DictionaryEntry wiki;
	private DictionaryEntry wordAPI;
	private DictionaryEntry merriam;
	
	public RetrieveEntry getOxford() {
		return oxford;
	}
	public void setOxford(RetrieveEntry oxford) {
		this.oxford = oxford;
	}
	public DictionaryEntry getWiki() {
		return wiki;
	}
	public void setWiki(DictionaryEntry wiki) {
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
