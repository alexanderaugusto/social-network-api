package br.inatel.icc.avl.model;

import java.util.ArrayList;
import java.util.List;

public class User {

	private Long id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private List<User> friends;
	
	public User() {
		friends = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}
	
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

	public List<User> getFriends() {
		return friends;
	}
}
