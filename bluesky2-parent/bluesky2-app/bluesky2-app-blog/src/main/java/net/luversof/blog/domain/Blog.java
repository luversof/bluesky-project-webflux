package net.luversof.blog.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * blog 정보
 * @author luver
 *
 */
@Data
@Document
public class Blog {

	@Id
	private long id;

	private UUID userId;
	
	@CreatedDate
	private LocalDateTime createdDate;

}
