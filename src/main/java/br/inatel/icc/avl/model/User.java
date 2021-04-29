package br.inatel.icc.avl.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String email;
	private String password;
	private String phone;

	@OneToMany(mappedBy = "owner", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Post> posts;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Reaction> reactions;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Comment> comments;

	@ManyToMany
	@JoinTable(name = "user_followers", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "follower_id") })
	private List<User> followers = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "user_followings", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "following_id") })
	private List<User> followings = new ArrayList<>();

	public User() {
	}

	public User(String name, String email, String password, String phone) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setPhone(String phone) {
		this.phone = phone;
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
		if (!followers.contains(user)) {
			followers.add(user);
		}
	}

	public void addFollowing(User user) {
		if (!followings.contains(user)) {
			followings.add(user);
		}
	}

	public void removeFollower(User user) {
		if (followers.contains(user)) {
			followers.remove(user);
		}
	}

	public void removeFollowing(User user) {
		if (followings.contains(user)) {
			followings.remove(user);
		}
	}
}
