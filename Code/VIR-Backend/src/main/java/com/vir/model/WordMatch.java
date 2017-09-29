package com.vir.model;

public class WordMatch {

	private String value;
	private String category;
	private String initialValue;
	
	public WordMatch(String value, String category, String initialValue) {
		super();
		this.value = value;
		this.category = category;
		this.initialValue = initialValue;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getInitialValue() {
		return initialValue;
	}
	public void setInitialValue(String initialValue) {
		this.initialValue = initialValue;
	}	
}
