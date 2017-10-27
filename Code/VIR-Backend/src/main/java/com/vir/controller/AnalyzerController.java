package com.vir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vir.exception.ApiError;
import com.vir.model.FileType;
import com.vir.model.Text;
import com.vir.service.FileProcessorService;
import com.vir.service.TextProcessorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Main controller for the Analyzer.
 * 
 * @author Alfredo Lopez
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags = "analyzer")
public class AnalyzerController {

	@Autowired
	@Qualifier("optimizedTextProcessorService")
	private TextProcessorService textProcessorService;

	@Autowired
	@Qualifier("simpleFileProcessorService")
	private FileProcessorService fileProcessorService;

	@ApiOperation(value = "Analyzes text input")
	@ApiResponse(code = 400, message = "Generic error", response = ApiError.class)
	@PostMapping(value = "/analyzeText", produces = MediaType.APPLICATION_JSON_VALUE)
	public Text analizeText(@RequestBody(required = true) String text) {
		return textProcessorService.process(text);
	}

	@ApiOperation(value = "Analyzes a file input.")
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = "Generic error", response = ApiError.class),
			@ApiResponse(code = 415, message = "Unsupported media type", response = ApiError.class),
			@ApiResponse(code = 406, message = "The content from the file cannot be parsed", response = ApiError.class),
			@ApiResponse(code = 422, message = "Page limit exeeded", response = ApiError.class)
			})
	@PostMapping(value = "/analyzeFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public Text analize(@RequestParam(name = "file", required = true) MultipartFile file,
			@RequestParam(name = "type", required = true) FileType type) throws Exception {

		return fileProcessorService.process(file, type);
	}
}