package exam.luisgiusti.codebreaker.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatsControllerSecurityTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void resetAuthUnauthorized() throws Exception {
		mockMvc.perform(put("/stats/reset/"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser
	public void resetAuthForbidden() throws Exception {
		mockMvc.perform(put("/stats/reset/"))
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(roles={"ADMIN"})
	public void resetAuthOK() throws Exception {
		mockMvc.perform(put("/stats/reset/"))
				.andExpect(status().isMovedPermanently());
	}
}
