package br.inatel.icc.lazy.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.NotNull;

import br.inatel.icc.lazy.model.User;

public class UserForm {
	@NotNull @NotEmpty
	private String name;
	@NotNull @NotEmpty @Email
	private String email;
	@NotNull @NotEmpty @Length(min = 8)
	private String password;
	private MultipartFile avatar;
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

	public MultipartFile getAvatar() {
		return avatar;
	}

	public User toUser(String avatarUrl) {
		String encryptedPassword = new BCryptPasswordEncoder().encode(password);
		return new User(name, email, encryptedPassword, "", avatarUrl);
	}
}
