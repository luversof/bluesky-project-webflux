package net.luversof.blog.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

/**
 * blog 정보
 * @author luver
 *
 */
@Data
public class Blog {

	private ObjectId id;

	private UUID userId;
	
	@CreatedDate
	private LocalDateTime createdDate;

	public String getIdString() {
		return id.toString();
	}
}
