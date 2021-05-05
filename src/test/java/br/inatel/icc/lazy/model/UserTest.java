package br.inatel.icc.lazy.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

	private User user1;
	private User user2;
	
	@BeforeEach
	public void beforeEach() {
		this.user1 = new User("Alexander", "alex@email.com", "123", "35984529203", "default-avatar.png");
		this.user2 = new User("Alysson", "alysson@email.com", "123", "35984029900", "default-avatar.png");
	}
	
	@Test
	void verifyIfUserIsFollowedByAnotherUser() {
		List<Follow> followers = new ArrayList<Follow>();
		followers.add(new Follow(user1, user2));
		user2.setFollowers(followers);
		
		boolean isFollowed = user2.isFollowedBy(user1);
		
		Assert.assertTrue(isFollowed);
	}
	
	@Test
	void verifyIfUserIsNotFollowedByAnotherUser() {
		List<Follow> followers = new ArrayList<Follow>();
		followers.add(new Follow(user1, user2));
		user2.setFollowers(followers);
		
		boolean isFollowed = user1.isFollowedBy(user2);
		
		Assert.assertFalse(isFollowed);
	}
}
