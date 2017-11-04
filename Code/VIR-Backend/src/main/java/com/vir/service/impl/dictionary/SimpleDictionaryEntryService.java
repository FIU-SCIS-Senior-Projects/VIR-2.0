package com.vir.service.impl.dictionary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vir.exception.UnableToGetEntryException;
import com.vir.model.Dictionary;
import com.vir.model.SourceType;
import com.vir.model.dictionary.oxford.RetrieveEntry;
import com.vir.model.dictionary.wiki.WikiEntry;
import com.vir.service.DictionaryEntryService;
import com.vir.service.WordService;
 
/**
 * Service that collects all the resources definitions and puts them
 * together in a single object.
 * 
 * @author Alfredo Lopez
 *
 */

@Service
public class SimpleDictionaryEntryService {
	
	@Autowired
	@Qualifier("oxfordDictionaryEntryService")
	private DictionaryEntryService oxfordService;
	
	@Autowired
	@Qualifier("wikiDictionaryEntryService")
	private DictionaryEntryService wikiService;
	
	@Autowired
	@Qualifier("simpleWordService")
	private WordService wordService;

	public Dictionary getEntry(String wordId, List<SourceType> types) throws UnableToGetEntryException {
		
		Dictionary data = new Dictionary();
		wordId = wordService.clean(wordId); 
		
		for (SourceType sourceType : types) {
			switch (sourceType) {
			case OXFORD:
				data.setOxford((RetrieveEntry) oxfordService.getEntry(wordId));
				break;
			case WIKI:
				data.setWiki((WikiEntry) wikiService.getEntry(wordId));
				break;
			}
		}
		return data;
	}
}
