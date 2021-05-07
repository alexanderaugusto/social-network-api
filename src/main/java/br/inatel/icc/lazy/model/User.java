package br.inatel.icc.lazy.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private String phone;
	private String avatar;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Profile> profiles = new ArrayList<>();

	@OneToMany(mappedBy = "owner", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Post> posts = new ArrayList<>();;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Reaction> reactions = new ArrayList<>();;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Comment> comments = new ArrayList<>();;

	@OneToMany(mappedBy = "following", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Follow> followings = new ArrayList<>();
	
	@OneToMany(mappedBy = "follower", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Follow> followers = new ArrayList<>();

	public User() {
	}

	public User(String name, String email, String password, String phone, String avatar) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.avatar = avatar;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public List<User> getFollowers() {
		List<User> users = new ArrayList<>();
		followers.forEach(follow -> {
			users.add(follow.getFollowing());
		});
		return users;
	}

	public void setFollowers(List<Follow> followers) {
		this.followers = followers;
	}

	public List<User> getFollowings() {
		List<User> users = new ArrayList<>();
		followings.forEach(follow -> {
			users.add(follow.getFollower());
		});
		return users;
	}
	
	public void setFollowings(List<Follow> followings) {
		this.followings = followings;
	}
	
	public boolean isFollowedBy(User user) {
		List<User> users = getFollowers();
		return users.contains(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.profiles;
	}

	@Override
	public String getUsername() {
		return this.email;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
