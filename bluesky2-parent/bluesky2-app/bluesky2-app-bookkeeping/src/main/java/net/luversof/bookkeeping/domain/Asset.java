package net.luversof.bookkeeping.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.luversof.bookkeeping.constant.AssetType;

/**
 * 자산 정보 자산의 타입과 현재 금액을 관리
 * 
 * @author bluesky
 *
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Asset {

	@NonNull
	@NotNull(groups = Update.class)
	private ObjectId id;

	private long amount;

	@NonNull
	@NotNull(groups = { Create.class, Update.class })
	private AssetType assetType;

	@NonNull
	@NotEmpty(groups = { Create.class, Update.class })
	private String name;

	public interface Create {
	}

	public interface Update {
	}

}
