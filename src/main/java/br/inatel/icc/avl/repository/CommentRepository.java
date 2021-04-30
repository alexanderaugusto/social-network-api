package br.inatel.icc.avl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.avl.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
