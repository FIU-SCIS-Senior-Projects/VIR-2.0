package com.vir.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vir.model.Word;
import com.vir.repository.WordRepository;

@RestController
@RequestMapping("/api/admin/words")
public class WordController {
	
	@Autowired
	WordRepository wordRepository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	Page<Word> findAll(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="size", defaultValue="20") int size,
			@RequestParam(value="sortKey", defaultValue="value") String sortField,
			@RequestParam(value="sortDirection", defaultValue="ASC") Direction direction
			){
		
		Sort sort = new Sort(direction, sortField);
		PageRequest pageRequest = new PageRequest(page, size, sort);
		return wordRepository.findAll(pageRequest);
	}
	
	@RequestMapping(method= {RequestMethod.POST, RequestMethod.PUT}, produces = MediaType.APPLICATION_JSON_VALUE)
	Word save(@RequestBody(required=true) Word word){
		
		Word existing = wordRepository.findFirstByValue(word.getValue());
		if (existing != null) {
			word.setId(existing.getId());
		}
		return wordRepository.save(word);
	}
	
	@GetMapping(value = "{value}", produces = MediaType.APPLICATION_JSON_VALUE)
	Word findOneByValue(@PathVariable(name="value", required=true) String value) {
		
		Word word = wordRepository.findFirstByValue(value);
		
		if (word != null) {
			return wordRepository.findFirstByValue(value);
		} else {
			throw new EntityNotFoundException("Word not found!");
		}
	}
	
	@DeleteMapping(value="{value}", produces = MediaType.APPLICATION_JSON_VALUE)
	void delete(@PathVariable(name="value", required=true) String value){
		wordRepository.removeByValue(value);
	}
}
