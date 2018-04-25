package exam.luisgiusti.codebreaker.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import exam.luisgiusti.codebreaker.TestConstants;
import exam.luisgiusti.codebreaker.domain.CarbonUnit;
import exam.luisgiusti.codebreaker.services.CarbonUnitDataService;
import exam.luisgiusti.codebreaker.services.DNAAnalyzerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MutantControllerTest {
	@InjectMocks
	private MutantController controller;
	@Mock
	private DNAAnalyzerService dnaAnalyzer;
	@Mock
	private CarbonUnitDataService dataService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void mutantResponseOK() throws Exception {
		CarbonUnit mutant = new CarbonUnit(TestConstants.MUTANT_DNA, true);

		when(dnaAnalyzer.isMutant(any(String[].class))).thenReturn(true);
		when(dataService.saveCarbonUnit(any(CarbonUnit.class))).thenReturn(mutant);

		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(mutant)))
				.andExpect(status().isOk());

		verify(dnaAnalyzer, times(1)).isMutant(any(String[].class));
		verify(dataService, times(1)).saveCarbonUnit(any(CarbonUnit.class));
		verifyNoMoreInteractions(dnaAnalyzer, dataService);
	}

	@Test
	public void noMutantResponseForbidden() throws Exception {
		CarbonUnit mutant = new CarbonUnit(TestConstants.HUMAN_DNA, false);
		when(dnaAnalyzer.isMutant(any(String[].class))).thenReturn(false);
		when(dataService.saveCarbonUnit(any(CarbonUnit.class))).thenReturn(mutant);

		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(mutant)))
				.andExpect(status().isForbidden());

		verify(dnaAnalyzer, times(1)).isMutant(any(String[].class));
		verify(dataService, times(1)).saveCarbonUnit(any(CarbonUnit.class));
		verifyNoMoreInteractions(dnaAnalyzer, dataService);
	}

	@Test
	public void noMutantResponseForbidden2() throws Exception {
		CarbonUnit mutant = new CarbonUnit(TestConstants.HUMAN_DNA_2, true);
		when(dnaAnalyzer.isMutant(any(String[].class))).thenReturn(false);
		when(dataService.saveCarbonUnit(any(CarbonUnit.class))).thenReturn(mutant);

		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(mutant)))
				.andExpect(status().isForbidden());

		verify(dnaAnalyzer, times(1)).isMutant(any(String[].class));
		verify(dataService, times(1)).saveCarbonUnit(any(CarbonUnit.class));
		verifyNoMoreInteractions(dnaAnalyzer, dataService);
	}

	private static String asJsonString(final Object obj) {
		try {
			return (new ObjectMapper()).writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
