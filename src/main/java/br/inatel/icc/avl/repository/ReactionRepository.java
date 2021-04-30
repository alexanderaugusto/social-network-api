package br.inatel.icc.avl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.avl.model.Post;
import br.inatel.icc.avl.model.Reaction;
import br.inatel.icc.avl.model.User;

public interface ReactionRepository extends JpaRepository<Reaction, Long>{

	Reaction findByUserAndPost(User user, Post post);
}	
