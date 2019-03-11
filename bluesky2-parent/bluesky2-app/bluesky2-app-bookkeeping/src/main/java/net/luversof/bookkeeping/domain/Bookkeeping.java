package net.luversof.bookkeeping.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;

@Data
public class Bookkeeping {

	private ObjectId id;

	private UUID userId;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	@DBRef		
	private List<Asset> assetList = new ArrayList<>();
}
