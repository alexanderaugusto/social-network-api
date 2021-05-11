//package br.inatel.icc.lazy.integration.controller;
//
//import static org.hamcrest.CoreMatchers.containsString;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//class PostControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@Test
//	@WithMockUser(username = "alexaasf1010@gmail.com", password = "123", roles = "USER")
//	public void shouldCreateNewPost() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders
//				.post("/posts")
//				.param("description", "This is a new post")
//				.contentType(MediaType.MULTIPART_FORM_DATA))
//		.andExpect(MockMvcResultMatchers.status().is(201))
//		.andExpect(MockMvcResultMatchers.content().string(containsString("id")))
//		.andExpect(MockMvcResultMatchers.content().string(containsString("description")))
//		.andExpect(MockMvcResultMatchers.content().string(containsString("media")));
//	}
//}
