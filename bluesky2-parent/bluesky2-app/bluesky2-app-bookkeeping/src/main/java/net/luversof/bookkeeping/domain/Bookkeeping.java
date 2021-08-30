package net.luversof.bookkeeping.domain;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Bookkeeping {

	@Id
//	@GeneratedValue(generator = "uuid-gen")
//	@GenericGenerator(name = "uuid-gen", strategy = "uuid2")
//	@Column(length = 16)
	private UUID id;

	@NotBlank(groups = { Create.class, Update.class })
	private String name;

//	@Column("user_id")
	private UUID userId;

	/**
	 * 시작일. startDay라고 해야하나?
	 */
	@Range(min = 1, max = 28, groups = { Create.class, Update.class })
	private int baseDate = 1;

	public interface Create {
	}

	public interface Update {
	}

	public interface Search {
	}
}