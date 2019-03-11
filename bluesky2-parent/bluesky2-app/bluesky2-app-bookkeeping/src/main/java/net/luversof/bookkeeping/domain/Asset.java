package net.luversof.bookkeeping.domain;

import org.bson.types.ObjectId;

import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@RequiredArgsConstructor
public class Asset {

	@NonNull
	private ObjectId id;
	
	private long amount;
	
	@NonNull
	private AssetType assetType;
	
	@NonNull
	private String name;
}
