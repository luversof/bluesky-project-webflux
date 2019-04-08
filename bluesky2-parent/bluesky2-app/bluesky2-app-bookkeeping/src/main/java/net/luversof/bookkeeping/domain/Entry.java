package net.luversof.bookkeeping.domain;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class Entry {

	private ObjectId id;
	
	private ObjectId bookkeepingId;
	
	private LocalDateTime entryDate;
	
	private ObjectId assetId;
	
	private long amount;
	
	private String memo;
	
}
