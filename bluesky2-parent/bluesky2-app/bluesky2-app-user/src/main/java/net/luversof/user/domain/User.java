package net.luversof.user.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Data
public class User {
	
	private ObjectId id;
	
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
