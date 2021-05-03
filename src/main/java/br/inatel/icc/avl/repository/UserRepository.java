package br.inatel.icc.avl.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.avl.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Page<User> findByNameContainingIgnoreCase(String userName, Pageable pageable);
	
	Optional<User> findByEmail(String email);
}
