package br.inatel.icc.avl.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.inatel.icc.avl.model.User;
import br.inatel.icc.avl.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService {

	private UserRepository userRepository;
	
	@Autowired
	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(username);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UsernameNotFoundException("Invalid data!");
	}

}
