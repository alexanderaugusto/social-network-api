package br.inatel.icc.lazy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.icc.lazy.config.security.TokenService;
import br.inatel.icc.lazy.controller.dto.TokenDto;
import br.inatel.icc.lazy.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private AuthenticationManager authManager;
	private TokenService tokenService;
	
	@Autowired
	public AuthController(AuthenticationManager authManager, TokenService tokenService) {
		this.authManager = authManager;
		this.tokenService = tokenService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken loginData = form.toLoginData();
		
		try {
			Authentication authentication = authManager.authenticate(loginData);
			
			String token = tokenService.generateToken(authentication);
					
			return ResponseEntity.status(200).body(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.status(400).build();
		}
		
	}
}
