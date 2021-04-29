package br.inatel.icc.avl.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.icc.avl.model.User;

public class UserDto {

	private Long id;
	private String name;
	private String email;
	private String phone;
	
	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.phone = user.getPhone();
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
	
	public String getPhone() {
		return phone;
	}
	
	public static List<UserDto> toDtoList(List<User> users){
		List<UserDto> usersDto = users.stream().map(UserDto::new).collect(Collectors.toList());
		return usersDto;
	}
}
