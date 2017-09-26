package com.vir.service.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jruby.ir.operands.Array;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.vir.model.FileType;
import com.vir.model.Text;
import com.vir.model.Word;
import com.vir.service.FileProcessorService;
import com.vir.service.WordService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocProcessorServiceTest {
	
	@Autowired
	@Qualifier("docProcessorService")
	private FileProcessorService docProcessorService;

	@Test
	public void process_WithDocFile_ReturnsString() throws Exception {
		final String filePath= "a_christmas_carol_by_charles_dickens_segment.doc";
		checkValues(filePath);
	}

	
	@Test
	public void process_WithDocxFile_ReturnsString() throws Exception {
		final String filePath= "a_christmas_carol_by_charles_dickens_segment.docx";
		checkValues(filePath);
	}
	
	private void checkValues(String filePath) throws Exception {
		InputStream stream = new ClassPathResource(filePath).getInputStream();
		MockMultipartFile file = new MockMultipartFile(filePath, stream);
		Text actual = docProcessorService.process(file, FileType.DOC);
		
		assertEquals("Marley", actual.getWords().get(0).getInitialValue());
		assertEquals("was", actual.getWords().get(1).getInitialValue());
		assertEquals("dead,", actual.getWords().get(2).getInitialValue());
	}
}
