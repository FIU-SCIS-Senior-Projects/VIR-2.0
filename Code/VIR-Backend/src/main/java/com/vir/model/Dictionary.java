package com.vir.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vir.model.dictionary.oxford.RetrieveEntry;
import com.vir.model.dictionary.wiki.WikiEntry;

/**
 * Class to hole the definitions data.
 * 
 * @author Alfredo Lopez
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dictionary {

	private RetrieveEntry oxford;
	private WikiEntry wiki;
	
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
}