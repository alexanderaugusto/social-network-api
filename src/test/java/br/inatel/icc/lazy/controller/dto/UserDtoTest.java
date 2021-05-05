package br.inatel.icc.lazy.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.inatel.icc.lazy.model.Follow;
import br.inatel.icc.lazy.model.User;

class UserDtoTest {
	
	private User user1;
	private User user2;
	
	@BeforeEach
	public void beforeEach() {
		this.user1 = new User("Alexander", "alex@email.com", "123", "35984529203", "default-avatar.png");
		this.user2 = new User("Alysson", "alysson@email.com", "123", "35984029900", "default-avatar.png");
	}

	@Test
	void checkUserTotalFollowers() {
		List<Follow> followers = new ArrayList<>();
		followers.add(new Follow(user2, user1));
		user1.setFollowers(followers);
		
		UserDto userDto = new UserDto(user1);
		
		Assert.assertEquals(1, userDto.getTotalFollowers());
	}
	
	@Test
	void checkUserTotalFollowings() {
		List<Follow> followings = new ArrayList<>();
		followings.add(new Follow(user1, user2));
		user1.setFollowings(followings);
		
		UserDto userDto = new UserDto(user1);
		
		Assert.assertEquals(1, userDto.getTotalFollowings());
	}
}
