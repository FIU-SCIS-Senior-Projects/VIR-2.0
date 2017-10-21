package com.vir.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DictionaryControlerTest {

	// @formatter:off
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getEntry_Endpoint_Returns200Ok() throws Exception {
		final String url = "/api/entries/apple?source=WIKI";
		final String expected = "{\n" + 
				"  \"wiki\": {\n" + 
				"    \"html\": \"<h3><span id=\\\"Etymology\\\">Etymology</span></h3>\\n<p>From <span>Middle English</span> <i class=\\\"Latn mention\\\" lang=\\\"enm\\\">appel</i>, from <span>Old English</span> <i class=\\\"Latinx mention\\\" lang=\\\"ang\\\">æppel</i> <span>(</span><span>“</span><span>apple, any kind of fruit, fruit in general, apple of the eye, ball, anything round, bolus, pill</span><span>”</span><span>)</span>, from <span>Proto-Germanic</span> <i class=\\\"Latinx mention\\\" lang=\\\"gem-pro\\\">*aplaz</i> <span>(</span><span>“</span><span>apple</span><span>”</span><span>)</span> (compare <span>Scots</span> <i class=\\\"Latn mention\\\" lang=\\\"sco\\\">aipple</i>, <span>West Frisian</span> <i class=\\\"Latn mention\\\" lang=\\\"fy\\\">apel</i>, <span>Dutch</span> <i class=\\\"Latn mention\\\" lang=\\\"nl\\\">appel</i>, <span>German</span> <i class=\\\"Latn mention\\\" lang=\\\"de\\\">Apfel</i>, <span>Swedish</span> <i class=\\\"Latn mention\\\" lang=\\\"sv\\\">äpple</i>), from <span>Proto-Indo-European</span> <i class=\\\"Latinx mention\\\" lang=\\\"ine-pro\\\">*h₂ébōl</i>, <i class=\\\"Latinx mention\\\" lang=\\\"ine-pro\\\">*h₂ébl̥</i> <span>(</span><span>“</span><span>apple</span><span>”</span><span>)</span> (compare <span>Irish</span> <i class=\\\"Latn mention\\\" lang=\\\"ga\\\">úll</i>, <span>Lithuanian</span> <i class=\\\"Latn mention\\\" lang=\\\"lt\\\">óbuolỹs</i>, <span>Russian</span> <i class=\\\"Cyrl mention\\\" lang=\\\"ru\\\">я́блоко</i> <span>(</span><span lang=\\\"ru-Latn\\\">jábloko</span><span>)</span>, possibly <span>Ancient Greek</span> <i class=\\\"polytonic mention\\\" lang=\\\"grc\\\">ἄμπελος</i> <span>(</span><span lang=\\\"grc-Latn\\\">ámpelos</span>, <span>“</span><span>vine</span><span>”</span><span>)</span>).</p>\\n<h3><span id=\\\"Pronunciation\\\">Pronunciation</span></h3>\\n<ul><li><span>(</span><span>US<span>,</span> UK</span><span>)</span> enPR: <span>ăpʹ(ə)l</span>, IPA<sup>(key)</sup>: <span>/ˈæ.pəl/</span>, <span>[ˈæ.pl̩]</span></li>\\n<li>Rhymes: <span>-æpəl</span></li>\\n</ul><h3><span id=\\\"Noun\\\">Noun</span></h3>\\n<p><strong class=\\\"Latn headword\\\" lang=\\\"en\\\">apple</strong> (<i>plural</i> <b class=\\\"Latn form-of lang-en plural-form-of\\\" lang=\\\"en\\\">apples</b>)</p>\\n<ol><li>A common, round fruit produced by the tree <i>Malus domestica</i>, cultivated in temperate climates. <span>[from 9th c.]</span>\\n<ul><li><b>c. 1378</b>, William Langland, <i>Piers Plowman</i>:\\n<dl><dd>I prayed pieres to pulle adown an <b>apple</b>.</dd>\\n</dl></li>\\n<li><b>2013</b>, John Vallins, <i>The Guardian</i>, 28 Oct 2013:\\n<dl><dd>Close by and under cover, I watched the juicing process. <b>Apples</b> were washed, then tipped, stalks and all, into the crusher and reduced to pulp.</dd>\\n</dl></li>\\n</ul></li>\\n<li>Any of various tree-borne fruits or vegetables especially considered as resembling an apple; also (with qualifying words) used to form the names of other specific fruits such as <i class=\\\"Latn mention\\\" lang=\\\"en\\\">custard apple</i>, <i class=\\\"Latn mention\\\" lang=\\\"en\\\">thorn apple</i> etc. <span>[from 9th c.]</span>\\n<ul><li><b>1658</b>, trans. Giambattista della Porta, <i>Natural Magick</i>, I.16:\\n<dl><dd>In Persia there grows a deadly tree, whose <b>Apples</b> are Poison, and present death.</dd>\\n</dl></li>\\n<li><b>1784</b>, James Cook, <i>A Voyage to the Pacific Ocean</i>, II:\\n<dl><dd>Otaheite […] is remarkable for producing great quantities of that delicious fruit we called <b>apples</b>, which are found in none of the others, except Eimeo.</dd>\\n</dl></li>\\n<li><b>1825</b>, Theodric Romeyn Beck, <i>Elements of Medical Jurisprudence</i>, 2nd edition, page 565:\\n<dl><dd><i>Hippomane mancinella.</i> (Manchineel-tree.) Dr. Peysonnel relates that a soldier, who was a slave with the Turks, eat some of the <b>apples</b> of this tree, and was soon seized with a swelling and pain of the abdomen.</dd>\\n</dl></li>\\n</ul></li>\\n<li>The fruit of the Tree of Knowledge, eaten by Adam and Eve according to post-Biblical Christian tradition; the forbidden fruit. <span>[from 11th c.]</span>\\n<ul><li><b>1667</b>, John Milton, <i>Paradise Lost</i>, Book X:\\n<dl><dd>Him by fraud I have seduced / From his Creator; and, the more to encrease / Your wonder, with an <b>apple</b> […].</dd>\\n</dl></li>\\n</ul></li>\\n<li>A tree of the genus <i>Malus</i>, especially one cultivated for its edible fruit; the apple tree. <span>[from 15th c.]</span>\\n<ul>\\n<li><b>2000</b> PA Thomas, <i>Trees: Their Natural History</i>, page 227:\\n<dl><dd>This allows a weak plant to benefit from the strong roots of another, or a vigorous tree (such as an <b>apple</b>) to be kept small by growing on 'dwarfing rootstock'.</dd>\\n</dl></li>\\n</ul></li>\\n<li>The wood of the apple tree. <span>[from 19th c.]</span></li>\\n<li><span>(</span><span>in the plural<span>,</span> Cockney rhyming slang</span><span>)</span> Short for <b>apples and pears</b>, slang for stairs. <span>[from 20th c.]</span></li>\\n<li><span>(</span><span>baseball<span>,</span> slang<span>,</span> obsolete</span><span>)</span> The ball in baseball. <span>[from 20th c.]</span></li>\\n<li><span>(</span><span>informal</span><span>)</span> When smiling, the round, fleshy part of the cheeks between the eyes and the corners of the mouth.</li>\\n<li><span>(</span><span>pejorative<span>,</span> ethnic slur</span><span>)</span> A Native American or red-skinned person who acts and/or thinks like a white (Caucasian) person.\\n<ul>\\n</ul></li>\\n</ol><h4><span id=\\\"Derived_terms\\\">Derived terms</span></h4>\\n<h4><span id=\\\"Translations\\\">Translations</span></h4>\\n<h3><span id=\\\"See_also\\\">See also</span></h3>\\n<ul><li><span lang=\\\"en\\\">malic</span></li>\\n<li><span>(</span><span>ethnic slur</span><span>)</span><span>:</span> <span lang=\\\"en\\\">coconut</span>, <span lang=\\\"en\\\">Oreo</span>, <span lang=\\\"en\\\">banana</span>, <span lang=\\\"en\\\">Twinkie</span></li>\\n</ul><h3><span id=\\\"References\\\">References</span></h3>\\n<h3><span id=\\\"Anagrams\\\">Anagrams</span></h3>\\n<ul><li><span lang=\\\"en\\\">appel</span>, <span lang=\\\"en\\\">pepla</span></li>\\n</ul>\"\n" + 
				"  }\n" + 
				"}";
		
		mockMvc.perform(get(url))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(content().json(expected));
	}
	
	// @formatter:on
}
