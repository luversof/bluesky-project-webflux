package net.luversof.user.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.Data;

@Data
@CompoundIndexes({
    @CompoundIndex(name = "userName_userType", def = "{'userName': 1, 'userType': -1}", unique = true)
})
public class User {
	
	private ObjectId id;
	
	@Indexed(unique = true)
	private UUID userId;
	
	@Indexed(unique = true)
	private String username;
	
	private String password;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    

	private List<UserAuthority> userAuthorityList;
	
	private UserType userType;
	
	private String externalId;

	public String getIdString() {
		return id.toString();
	}
}
