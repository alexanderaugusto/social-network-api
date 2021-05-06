package br.inatel.icc.lazy.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.inatel.icc.lazy.model.User;

public class UserDto {

	private Long id;
	private String name;
	private String email;
	private String phone;
	private String avatar;
	private int totalFollowers;
	private int totalFollowings;
	private int totalPosts;
	
	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.avatar = user.getAvatar();
		this.totalFollowers = user.getFollowers().size();
		this.totalFollowings = user.getFollowings().size();
		this.totalPosts = user.getPosts().size();
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
	
	public String getAvatar() {
		return avatar;
	}

	public int getTotalFollowers() {
		return totalFollowers;
	}

	public int getTotalFollowings() {
		return totalFollowings;
	}
	
	public int getTotalPosts() {
		return totalPosts;
	}

	public static List<UserDto> toDtoList(List<User> users){
		List<UserDto> usersDto = users.stream().map(UserDto::new).collect(Collectors.toList());
		return usersDto;
	}

	public static Page<UserDto> toDtoPage(Page<User> users) {
		Page<UserDto> usersDto = users.map(UserDto::new);
		return usersDto;
	}
}
