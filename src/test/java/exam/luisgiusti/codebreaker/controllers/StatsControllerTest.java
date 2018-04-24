package exam.luisgiusti.codebreaker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import exam.luisgiusti.codebreaker.domain.Stats;
import exam.luisgiusti.codebreaker.services.CarbonUnitDataService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StatsControllerTest {
	@InjectMocks
	private StatsController controller;
	@Mock
	private CarbonUnitDataService carbonUnitDataService;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void getStats() throws Exception {
		Stats stats = new Stats(1L, 1L);
		when(carbonUnitDataService.getStats()).thenReturn(Optional.of(stats));

		mockMvc.perform(get("/stats/"))
				.andExpect(status().isOk())
				.andExpect(content().json(asJsonString(stats)));

		verify(carbonUnitDataService, times(1)).getStats();
		verifyNoMoreInteractions(carbonUnitDataService);
	}

	@Test
	public void getNoStats() throws Exception {
		when(carbonUnitDataService.getStats()).thenReturn(Optional.empty());

		mockMvc.perform(get("/stats/"))
				.andExpect(status().isNoContent());

		verify(carbonUnitDataService, times(1)).getStats();
		verifyNoMoreInteractions(carbonUnitDataService);
	}

	@Test
	public void resetStats() throws Exception {
		mockMvc.perform(put("/stats/reset/"))
				.andExpect(status().isMovedPermanently())
				.andExpect(redirectedUrl("/stats/"));

		verify(carbonUnitDataService, times(1)).deleteAll();
		verifyNoMoreInteractions(carbonUnitDataService);
	}

	private static String asJsonString(final Object obj) {
		try {
			return (new ObjectMapper()).writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}