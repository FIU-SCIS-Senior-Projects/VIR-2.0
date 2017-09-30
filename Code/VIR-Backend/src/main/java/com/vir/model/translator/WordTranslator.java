package com.vir.model.translator;

import org.apache.commons.csv.CSVRecord;

import com.vir.model.Word;

public class WordTranslator {
	
	public static Word fromCSVRecordToWord(CSVRecord csvRecord) {
		String value = csvRecord.get("value");
		String category = csvRecord.get("category");
		return new Word(value, category);
	}
}
