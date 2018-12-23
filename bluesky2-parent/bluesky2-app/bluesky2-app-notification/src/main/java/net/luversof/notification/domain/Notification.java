package net.luversof.notification.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Notification {
	
	@Id
	private long id;
	
	private String message;
}
