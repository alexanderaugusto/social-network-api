package br.inatel.icc.lazy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.lazy.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
