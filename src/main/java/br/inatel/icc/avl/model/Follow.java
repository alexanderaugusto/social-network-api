package br.inatel.icc.avl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User following;
	@ManyToOne
	private User follower;
	
	public Follow() {
	}
	
	public Follow(User following, User follower) {
		this.following = following;
		this.follower = follower;
	}

	public Long getId() {
		return id;
	}

	public User getFollowing() {
		return following;
	}
	
	public User getFollower() {
		return follower;
	}
}
