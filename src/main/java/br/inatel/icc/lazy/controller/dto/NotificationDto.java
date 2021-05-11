package br.inatel.icc.lazy.controller.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.inatel.icc.lazy.model.Notification;

public class NotificationDto {

	private Long id;
	private String description;
	private String url;
	private UserDto sender;
	
	public NotificationDto(Notification notification) {
		this.id = notification.getId();
		this.description = notification.getDescription();
		this.url = notification.getUrl();
		this.sender = new UserDto(notification.getSender());
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

	public UserDto getSender() {
		return sender;
	}

	public static List<NotificationDto> toDtoList(List<Notification> notifications) {
		Collections.reverse(notifications);
		List<NotificationDto> notificationsDto = notifications.stream().map(NotificationDto::new).collect(Collectors.toList());
		return notificationsDto;
	}
}
