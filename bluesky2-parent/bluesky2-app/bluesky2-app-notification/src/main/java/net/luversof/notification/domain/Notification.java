package net.luversof.notification.domain;


import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class Notification {
	
	private ObjectId id;
	
	private String message;
	
	public String getIdString() {
		return id.toString();
	}
}
