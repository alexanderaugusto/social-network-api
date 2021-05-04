package br.inatel.icc.lazy.integration.controller;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldDoALogin() throws Exception {
		String data = "{\"email\":\"alexaasf1010@gmail.com\", \"password\":\"123\"}";
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/auth/login")
				.content(data)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().string(containsString("token")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("type")));
	}
	
	@Test
	public void shouldNotDoALogin() throws Exception {
		String data = "{\"email\":\"alexaasf1010@gmail.com\", \"password\":\"123\"}";
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/auth/login")
				.content(data)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}

}
