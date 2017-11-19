package com.vir.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vir.model.Word;
import com.vir.repository.WordRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/words")
@Api(tags = "words")
public class WordController {

	@Autowired
	WordRepository wordRepository;
	
	@ApiOperation("Retrieves a list of words by category")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Word> findAll (
			@RequestParam(value = "category", required = true) String category,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "20") int size,
			@RequestParam(value = "sortKey", defaultValue = "value") String sortField,
			@RequestParam(value = "sortDirection", defaultValue = "ASC") Direction direction) {

		PageRequest pageRequest = new PageRequest(page, size, new Sort(direction, sortField));
		return wordRepository.findAllByCategory(pageRequest, category);
	}
	
	@ApiOperation("Finds a word by value")
	@GetMapping(value = "{value}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Word findOneByValue(@PathVariable(name="value", required=true) String value) {
		
		Word word = wordRepository.findFirstByValue(value);
		
		if (word != null) {
			return wordRepository.findFirstByValue(value);
		} else {
			throw new EntityNotFoundException("Word not found!");
		}
	}
}
