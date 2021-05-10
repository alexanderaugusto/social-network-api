package br.inatel.icc.lazy.acceptance.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.inatel.icc.lazy.controller.UserController;
import br.inatel.icc.lazy.controller.form.UserForm;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@WebMvcTest(UserController.class)
@AutoConfigureWebMvc
@ActiveProfiles("test")
public class UserSteps {
	
	@Autowired
	private MockMvc mockMvc;

	private UserForm data = new UserForm();
	private ResultActions response;
	
	@Given("user data to create an account")
	public void user_data_to_create_an_account() {
	    data.setName("Alexander Augusto");
	    data.setEmail("alex@email.com");
	    data.setPassword("12345678");
	}
	
	@When("user post then on the endpoint \\/users")
	public void user_post_then_on_the_endpoint_users() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/auth/login")
				.param("name", data.getName())
				.param("email", data.getEmail())
				.param("password", data.getPassword())
				.contentType(MediaType.MULTIPART_FORM_DATA));
	}
	
	@Then("server return 201 and created user data")
	public void server_return_and_created_user_data() throws Exception {
		response.andExpect(MockMvcResultMatchers.status().is(201));
	}
}
