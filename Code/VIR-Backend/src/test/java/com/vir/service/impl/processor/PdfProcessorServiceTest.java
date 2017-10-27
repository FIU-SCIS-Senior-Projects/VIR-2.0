package com.vir.service.impl.processor;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.vir.exception.PageLimitExceededException;
import com.vir.model.FileType;
import com.vir.model.Text;
import com.vir.service.FileProcessorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfProcessorServiceTest {

	@Autowired
	@Qualifier("pdfProcessorService")
	private FileProcessorService pdfProcessorService;

	@Test
	public void process_WithPdfFile_ReturnsString() throws Exception {
		final String filePath = "a_christmas_carol_by_charles_dickens_segment.pdf";
		checkValues(filePath);
	}

	@Test(expected = PageLimitExceededException.class)
	public void process_WithPdfFileWith101Pages_ThrowsPageLimitExceededException() throws Exception {

		final MockMultipartFile file = get101PdfPagesMock();
		pdfProcessorService.process(file, FileType.PDF);
	}

	private MockMultipartFile get101PdfPagesMock() throws Exception {

		final int maxPages = 102;

		try (PDDocument document = new PDDocument(); 
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

			PDFont font = PDType1Font.HELVETICA;

			for (int i = 1; i < maxPages; i++) {

				PDPage page = new PDPage();
				try (PDPageContentStream content = new PDPageContentStream(document, page)) {
					content.setFont(font, 12);
					content.beginText();
					content.newLineAtOffset(100, 700);
					content.showText("Hello world");
					content.endText();
					content.close();
				}
				document.addPage(page);
			}

			document.save(outputStream);
			return new MockMultipartFile("101PagesPdf", outputStream.toByteArray());
		}
	}

	private void checkValues(String filePath) throws Exception {
		InputStream stream = new ClassPathResource(filePath).getInputStream();
		MockMultipartFile file = new MockMultipartFile(filePath, stream);
		Text actual = pdfProcessorService.process(file, FileType.PDF);

		assertEquals("Marley", actual.getWords().get(0).getInitialValue());
		assertEquals("was", actual.getWords().get(1).getInitialValue());
		assertEquals("dead,", actual.getWords().get(2).getInitialValue());
	}
}
