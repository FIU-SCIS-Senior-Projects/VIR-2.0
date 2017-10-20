package com.vir.model.dictionary.wiki;

import java.util.Map;

public class WikiResult {

	private String batchcomplete;
	private Map<String, Map<String, WikiPage>> query;
	
	public String getBatchcomplete() {
		return batchcomplete;
	}
	public void setBatchcomplete(String batchcomplete) {
		this.batchcomplete = batchcomplete;
	}
	public Map<String, Map<String, WikiPage>> getQuery() {
		return query;
	}
	public void setQuery(Map<String,  Map<String, WikiPage>> query) {
		this.query = query;
	}
	
}
