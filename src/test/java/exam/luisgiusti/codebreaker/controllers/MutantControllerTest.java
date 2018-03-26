package exam.luisgiusti.codebreaker.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import exam.luisgiusti.codebreaker.domain.CarbonUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MutantControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void mutantResponseOK() throws Exception {
		String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
		CarbonUnit cu = new CarbonUnit();
		cu.setDna(dna);

		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(cu)))
				.andExpect(status().isOk());
	}

	@Test
	public void noMutantResponseForbidden() throws Exception {
		String[] dna = {"ACACAC", "GTGTGT", "ACACAC", "GTGTGT", "ACACAC", "GTGTGT"};
		CarbonUnit cu = new CarbonUnit();
		cu.setDna(dna);

		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(cu)))
				.andExpect(status().isForbidden());
	}

	@Test
	public void wrongDNALengthResponseBadRequest() throws Exception {
		String[] dna = {"ACACACAAAA", "GTGTGT", "ACACAC", "GTGTGT", "ACACAC", "GTGTGT"};
		CarbonUnit cu = new CarbonUnit();
		cu.setDna(dna);

		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(cu)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void wrongDNACharacterResponseBadRequest() throws Exception {
		String[] dna = {"xCACAC", "GTGTGT", "ACACAC", "GTGTGT", "ACACAC", "GTGTGT"};
		CarbonUnit cu = new CarbonUnit();
		cu.setDna(dna);

		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(cu)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void wrongDNASizeResponseBadRequest() throws Exception {
		String[] dna = {"AAA", "CCC", "GGG"};
		CarbonUnit cu = new CarbonUnit();
		cu.setDna(dna);

		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(cu)))
				.andExpect(status().isBadRequest());
	}

	public static String asJsonString(final Object obj) {
		try {
			return (new ObjectMapper()).writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
