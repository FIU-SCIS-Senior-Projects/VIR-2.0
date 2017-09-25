package com.vir.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vir.model.FileType;
import com.vir.model.Text;
import com.vir.service.FileProcessorService;
import com.vir.service.TextProcessorService;

/**
 * Main controller for the Analyzer.
 * 
 * @author Alfredo Lopez
 *
 */
@RestController
@RequestMapping("/api")
public class AnalyzerController {

	private static final Logger LOG = LoggerFactory.getLogger(AnalyzerController.class);

	@Autowired
	private TextProcessorService textProcessorService;
	
	@Autowired
	@Qualifier("fileProcessorServer")
	private FileProcessorService fileProcessorService;

	@PostMapping(value = "/analyzeText", produces = MediaType.APPLICATION_JSON_VALUE)
	public Text analizeText(@RequestBody(required = true) String text) {
		return textProcessorService.process(text);
	}

	@PostMapping(value = "/analyzeFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public Text analize(
			@RequestParam(name = "file", required = true) MultipartFile file,
			@RequestParam(name = "type", required = true) FileType type) throws Exception {
		
		return fileProcessorService.process(file, type);
	}
}