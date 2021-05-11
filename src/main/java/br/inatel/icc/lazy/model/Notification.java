package br.inatel.icc.lazy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private String url;
	
	@ManyToOne
	private User sender;
	
	@ManyToOne
	private User receiver;
	
	public Notification() {	
	}

	public Notification(String description, String url, User sender, User receiver) {
		this.description = description;
		this.url = url;
		this.sender = sender;
		this.receiver = receiver;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public User getSender() {
		return sender;
	}

	public User getReceiver() {
		return receiver;
	}
}
