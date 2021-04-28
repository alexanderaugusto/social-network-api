package br.inatel.icc.avl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.avl.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
