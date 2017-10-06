package com.vir.model.dictionary.oxford;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetrieveEntry {

	private Object metadata;
	private List<HeadwordEntry> results;

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}

	public List<HeadwordEntry> getResults() {
		return results;
	}

	public void setResults(List<HeadwordEntry> results) {
		this.results = results;
	}
}
