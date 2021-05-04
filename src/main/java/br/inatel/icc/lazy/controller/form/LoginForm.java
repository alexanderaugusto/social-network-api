package br.inatel.icc.lazy.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

	public UsernamePasswordAuthenticationToken toLoginData() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
}
