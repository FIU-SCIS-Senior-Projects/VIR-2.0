package com.vir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vir.service.ResourceService;

@RestController
@RequestMapping("/api/admin/resources")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	@GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> download() {

		Resource file = resourceService.get();
		final String header = "attachment; filename=\"words.csv\"";

		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, header)
				.body(file);
	}

	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean upload(
			@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "replace", required = true, defaultValue = "false") Boolean replace) throws Exception {
		
		return resourceService.save(file, replace);
	}
}
