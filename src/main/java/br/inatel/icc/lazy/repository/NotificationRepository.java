package br.inatel.icc.lazy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.icc.lazy.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
	
}
