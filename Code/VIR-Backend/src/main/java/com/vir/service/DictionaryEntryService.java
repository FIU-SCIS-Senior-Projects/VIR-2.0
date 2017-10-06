package com.vir.service;

import org.springframework.stereotype.Service;

import com.vir.exception.UnableToGetEntryException;
import com.vir.model.dictionary.oxford.RetrieveEntry;

@Service
public interface DictionaryEntryService {
	public RetrieveEntry getEntry(String wordId) throws UnableToGetEntryException;
}
