package br.inatel.icc.lazy.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sun.istack.NotNull;

import br.inatel.icc.lazy.model.User;

public class UserForm {
	@NotNull @NotEmpty
	private String name;
	@NotNull @NotEmpty @Email
	private String email;
	@NotNull @NotEmpty @Length(min = 8)
	private String password;
	@NotEmpty @Length(min = 9)
	private String phone;
	
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
	
	public User toUser() {
		String encryptedPassword = new BCryptPasswordEncoder().encode(password);
		return new User(name, email, encryptedPassword, phone, "https://cdn4.iconfinder.com/data/icons/avatars-xmas-giveaway/128/batman_hero_avatar_comics-512.png");
	}
}
