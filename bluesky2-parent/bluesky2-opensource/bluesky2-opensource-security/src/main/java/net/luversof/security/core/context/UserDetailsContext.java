package net.luversof.security.core.context;

import java.io.Serializable;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsContext extends Serializable {

	UserDetails getUserDetails();
	
	void setUserDetails(UserDetails userDetails);

}
