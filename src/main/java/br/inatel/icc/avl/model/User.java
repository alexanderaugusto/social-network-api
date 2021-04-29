package br.inatel.icc.avl.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	private String phone;
	@OneToMany(mappedBy = "owner")
	private List<Post> posts;
	@ManyToMany
	@JoinTable(
            name = "user_followers",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "follower_id")}
    )
	private List<User> followers;
	@ManyToMany
	@JoinTable(
            name = "user_followings",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "following_id")}
    )
	private List<User> followings;
	
	public User() {}
	
	public User(String name, String email, String password, String phone) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		
		followers = new ArrayList<>();
		followings = new ArrayList<>();
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

	public List<Post> getPosts() {
		return posts;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public List<User> getFollowings() {
		return followings;
	}

	public void addFollower(User user) {
		if(!followers.contains(user)) {
			followers.add(user);
		}
	}
	
	public void addFollowing(User user) {
		if(!followings.contains(user)) {
			followings.add(user);
		}
	}
	
	public void removeFollower(User user) {
		if(followers.contains(user)) {
			followers.remove(user);
		}
	}
	
	public void removeFollowing(User user) {
		if(followings.contains(user)) {
			followings.remove(user);
		}
	}
}
