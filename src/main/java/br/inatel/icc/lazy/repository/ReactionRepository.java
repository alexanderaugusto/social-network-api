package br.inatel.icc.lazy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.lazy.model.Post;
import br.inatel.icc.lazy.model.Reaction;
import br.inatel.icc.lazy.model.User;

public interface ReactionRepository extends JpaRepository<Reaction, Long>{
	
	Optional<Reaction> findByUserAndPost(User user, Post post);
}	
