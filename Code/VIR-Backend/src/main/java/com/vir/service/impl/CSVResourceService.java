package com.vir.service.impl;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.util.InMemoryResource;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.vir.model.Word;
import com.vir.repository.WordRepository;
import com.vir.service.ResourceService;

@Service
public class CSVResourceService implements ResourceService {

	@Autowired
	private WordRepository wordRepository;

	@Override
	public Resource get() throws Exception {

		Iterable<Word> wordIterable = wordRepository.findAll();
		List<Word> words = new ArrayList<>();
		wordIterable.iterator().forEachRemaining(words::add);
		
		StringWriter sw = new StringWriter();
		try (CSVWriter csvWriter = new CSVWriter(sw)) {

			BeanToCsv<Word> bc = new BeanToCsv<>();
			ColumnPositionMappingStrategy<Word> mappingStrategy = new ColumnPositionMappingStrategy<>();
			String[] columns = new String[] { "id", "value", "category" };
			mappingStrategy.setColumnMapping(columns);
			// We need this otherwise we get an error.
			// No getting it from the generic yet.
			mappingStrategy.setType(Word.class);
			bc.write(mappingStrategy, csvWriter, words);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new InMemoryResource(sw.toString());
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

}
