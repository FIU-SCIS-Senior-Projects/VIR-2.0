package com.vir.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.util.InMemoryResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.vir.exception.HeaderMalformattedError;
import com.vir.model.Word;
import com.vir.model.translator.WordTranslator;
import com.vir.repository.WordRepository;
import com.vir.repository.WordRepositoryHelper;
import com.vir.service.ResourceService;

@Service
@SuppressWarnings("deprecation")
public class CSVResourceService implements ResourceService {

	@Autowired
	private WordRepository wordRepository;
	
	@Autowired
	private WordRepositoryHelper wordRepositoryHelper;

	private static final String HEADER_ID = "id";
	private static final String HEADER_VALUE = "value";
	private static final String HEADER_CATEGORY = "category";

	@Override
	public Resource get() {

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
	public Boolean save(MultipartFile file, Boolean replace) throws Exception {

		List<Word> words = new ArrayList<>();
		List<CSVRecord> records = parseRecords(file);
		
		if (!hasValidHeaders(records.get(0))) {
			String error = String.format("Headers should be specified as follows and in the same order:\n %s, %s",
					HEADER_VALUE, HEADER_CATEGORY);
			throw new HeaderMalformattedError(error);
		}

		long size = records.size();
		for (int i = 1; i < size; i++) {
			words.add(WordTranslator.fromCSVRecordToWord(records.get(i)));
		}
		
		if (replace) {
			wordRepositoryHelper.truncateWordTable();
		}
		
		wordRepository.save(words);

		return true;
	}

	private List<CSVRecord> parseRecords(MultipartFile file) throws IOException {
		String[] columns = new String[] { HEADER_VALUE, HEADER_CATEGORY };
		InputStreamReader is = new InputStreamReader(file.getInputStream());
		CSVParser parser = CSVFormat.EXCEL.withHeader(columns).parse(is);
		List<CSVRecord> records = parser.getRecords();
		return records;
	}

	private boolean hasValidHeaders(CSVRecord firstRecord) {
		return firstRecord.get(HEADER_VALUE).equals(HEADER_VALUE)
				&& firstRecord.get(HEADER_CATEGORY).equals(HEADER_CATEGORY);
	}
}
