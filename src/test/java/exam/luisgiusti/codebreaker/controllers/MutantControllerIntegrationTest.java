package exam.luisgiusti.codebreaker.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import exam.luisgiusti.codebreaker.TestConstants;
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
public class MutantControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void mutantResponseOK() throws Exception {
		CarbonUnit cu = new CarbonUnit(null, TestConstants.MUTANT_DNA, null);

		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(cu)))
				.andExpect(status().isOk());
	}

	@Test
	public void mutantResponseOK2() throws Exception {
		CarbonUnit cu = new CarbonUnit(null, TestConstants.MUTANT_DNA_2, null);

		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(cu)))
				.andExpect(status().isOk());
	}

	@Test
	public void noMutantResponseForbidden() throws Exception {
		CarbonUnit cu = new CarbonUnit(null, TestConstants.HUMAN_DNA, null);

		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(cu)))
				.andExpect(status().isForbidden());
	}

	@Test
	public void unbalancedDNAResponseBadRequest() throws Exception {
		CarbonUnit cu = new CarbonUnit(null, TestConstants.UNBALANCED_DNA, null);

		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(cu)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void wrongDNACharacterResponseBadRequest() throws Exception {
		CarbonUnit cu = new CarbonUnit(null, TestConstants.WRONG_CHARACTERS_DNA, null);

		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(cu)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void wrongDNASizeResponseBadRequest() throws Exception {
		CarbonUnit cu = new CarbonUnit(null, TestConstants.SMALL_DNA, null);

		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(cu)))
				.andExpect(status().isBadRequest());
	}

	private static String asJsonString(final Object obj) {
		try {
			return (new ObjectMapper()).writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}