package net.luversof.bookkeeping.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Data
public class Bookkeeping {

	private ObjectId id;

	private UUID userId;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	private List<Asset> assetList = new ArrayList<>();
}
