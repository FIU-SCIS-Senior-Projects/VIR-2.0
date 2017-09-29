package com.vir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vir.model.Word;
import com.vir.repository.WordRepository;

@RestController
@RequestMapping("/api/admin/words")
public class WordController {
	
	@Autowired
	WordRepository wordRepository;

	@GetMapping(value="/")
	Page<Word> findAll(Pageable page) {
		return wordRepository.findAll(page);
	}
}
