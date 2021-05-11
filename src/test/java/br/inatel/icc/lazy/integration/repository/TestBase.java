package br.inatel.icc.lazy.integration.repository;

import br.inatel.icc.lazy.model.Follow;
import br.inatel.icc.lazy.model.Post;
import br.inatel.icc.lazy.model.Reaction;
import br.inatel.icc.lazy.model.User;

public class TestBase {
	
	protected User userX;
	protected User userY;
	protected User userZ;
	protected Post postX;
	protected Post postY;
	protected Reaction reactionXY;
	protected Reaction reactionYX;
	protected Follow followXY;
	protected Follow followYX;
	
	public User getUserX() {
		if(userX == null) {
			userX = new User("User X", "x@email.com", "12345678", "(35) 99999-9999", "lazy/user/default-avatar.png");
		}
		
		return userX;
	}
	
	public User getUserY() {
		if(userY == null) {
			userY = new User("User Y", "y@email.com", "12345678", "(35) 99999-9999", "lazy/user/default-avatar.png");
		}
		
		return userY;
	}
	
	public User getUserZ() {
		if(userZ == null) {
			userZ = new User("User Z", "z@email.com", "12345678", "(35) 99999-9999", "lazy/user/default-avatar.png");
		}
		
		return userZ;
	}
	
	public Post getPostUserX() {
		if(postX == null) {
			postX = new Post("This is a new post", "lazy/post/default-post.jpg", this.getUserX());
		}
		
		return postX;
	}
	
	public Post getPostUserY() {
		if(postY == null) {
			postY = new Post("This is a new post", "lazy/post/default-post.jpg", this.getUserY());
		}
		
		return postY;
	}
	
	public Reaction getReactionUserXPostY() {
		if(reactionXY == null) {
			reactionXY = new Reaction(this.getUserX(), this.getPostUserY());
		}
		
		return reactionXY;
	}
	
	public Reaction getReactionUserYPostX() {
		if(reactionYX == null) {
			reactionYX = new Reaction(this.getUserY(), this.getPostUserX());
		}
		
		return reactionYX;
	}
	
	public Follow getUserXFollowUserY() {
		if(followXY == null) {
			followXY = new Follow(this.getUserX(), this.getUserY());
		}
		
		return followXY;
	}
	
	public Follow getUserYFollowUserX() {
		if(followYX == null) {
			followYX = new Follow(this.getUserY(), this.getUserX());
		}
		
		return followYX;
	}
}
