package br.inatel.icc.lazy.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.inatel.icc.lazy.model.User;

public class UserFormUpdate {

	@NotNull @NotEmpty @Length(min = 5)
	private String name;
	@NotEmpty @Length(min = 9)
	private String phone;
	
	public String getName() {
		return name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public User update(User user) {
		user.setName(name);
		user.setPhone(phone);
		return user;
	}
}
