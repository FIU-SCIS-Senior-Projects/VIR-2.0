package com.vir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vir.model.dictionary.oxford.RetrieveEntry;
import com.vir.service.DictionaryEntryService;

@RestController
@RequestMapping("/api")
public class DictionaryEntryController {

	@Autowired
	@Qualifier("oxfordDictionaryEntryService")
	private DictionaryEntryService dictionaryEntryService;

	@GetMapping(value = "/entries/{wordId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RetrieveEntry getEntry(@PathVariable("wordId") String wordId) throws Exception {
		return dictionaryEntryService.getEntry(wordId);
	}
}
