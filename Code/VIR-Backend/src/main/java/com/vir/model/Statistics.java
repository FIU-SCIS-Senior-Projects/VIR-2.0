package com.vir.model;

public class Statistics {

	private Count wordCount;
	private Percent wordPercentage;

	@SuppressWarnings("unused")
	private Statistics() {
	}

	public Statistics(Count wordCount, Percent wordPercentage) {
		this.wordCount = wordCount;
		this.wordPercentage = wordPercentage;
	}

	public Count getWordCount() {
		return wordCount;
	}
	public void setWordCount(Count wordCount) {
		this.wordCount = wordCount;
	}
	public Percent getWordPercentage() {
		return wordPercentage;
	}
	public void setWordPercentage(Percent wordPercentage) {
		this.wordPercentage = wordPercentage;
	}
}
