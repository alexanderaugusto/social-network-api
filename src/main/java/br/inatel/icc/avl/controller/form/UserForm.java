package br.inatel.icc.avl.controller.form;

import br.inatel.icc.avl.model.User;

public class UserForm {
	private String name;
	private String email;
	private String password;
	private String phone;
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public User toUser() {
		return new User(name, email, password, phone);
	}
}
