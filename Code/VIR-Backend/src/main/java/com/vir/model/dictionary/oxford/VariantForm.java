package com.vir.model.dictionary.oxford;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VariantForm {

	private List<String> regions;
	private String text;

	public List<String> getRegions() {
		return regions;
	}

	public void setRegions(List<String> regions) {
		this.regions = regions;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
