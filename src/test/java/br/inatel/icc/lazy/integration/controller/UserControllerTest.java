package br.inatel.icc.lazy.integration.controller;

import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldCreateAUser() throws Exception {
		String data = "{\"email\":\"alex@email.com\", \"name\":\"Alexander Augusto\", \"password\":\"12345678\", \"phone\":\"35984529203\"}";
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/users")
				.content(data)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(201))
		.andExpect(MockMvcResultMatchers.content().string(containsString("id")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("name")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("email")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("phone")));
	}

	@Test
	@WithMockUser(username = "alexaasf1010@gmail.com", password = "123", roles = "USER")
	public void shouldListAUser() throws Exception {
		String userId = "1";
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/users/" + userId)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().string(containsString("id")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("name")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("email")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("phone")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("totalFollowers")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("totalFollowings")));
	}
	
	@Test
	@WithMockUser(username = "alexaasf1010@gmail.com", password = "123", roles = "USER")
	public void shouldUpdateAUser() throws Exception {
		String userId = "1";
		String data = "{\"name\":\"Alexander\", \"phone\":\"35999999999\"}";
		
		mockMvc.perform(MockMvcRequestBuilders
				.put("/users/" + userId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(data))
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().string(containsString("id")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("name")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("email")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("phone")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("totalFollowers")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("totalFollowings")));
	}
	
	@Test
	@WithMockUser(username = "alexaasf1010@gmail.com", password = "123", roles = "USER")
	public void shouldDeleteAUser() throws Exception {
		String userId = "2";
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/users/" + userId)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(204));
	}
}
