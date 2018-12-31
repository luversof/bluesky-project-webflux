package net.luversof.user.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class UserAuthority {

	@Id
	private ObjectId idx;

	private String authority;
}
