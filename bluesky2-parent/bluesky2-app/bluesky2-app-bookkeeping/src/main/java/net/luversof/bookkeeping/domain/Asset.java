package net.luversof.bookkeeping.domain;

import java.util.UUID;

import org.bson.types.ObjectId;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.luversof.bookkeeping.constant.AssetType;

/**
 * 자산 정보
 * 자산의 타입과 현재 금액을 관리
 * @author bluesky
 *
 */
@Data
@RequiredArgsConstructor
public class Asset {

	private ObjectId id;
	
	@NonNull
	private UUID userId;
	
	private long amount;
	
	@NonNull
	private AssetType assetType;
	
	@NonNull
	private String name;
}
