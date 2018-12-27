package net.luversof.notification.domain;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Data
public class Notification {

	private ObjectId id;

	private String userId;

	private String message;

	@CreatedDate
	private LocalDateTime createdDate;

	public String getIdString() {
		return id.toString();
	}
}
