package net.luversof.security.core.userdetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.luversof.user.domain.User;
import net.luversof.user.domain.UserAuthority;
import net.luversof.user.domain.UserType;

@Data
@AllArgsConstructor
public class BlueskyUser implements UserDetails, CredentialsContainer {
	
	private static final long serialVersionUID = -7218355940538132953L;
	
	private final ObjectId id;
	private final String username;
	private String password;
	private final Collection<? extends GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final UserType userType;
    private final String externalId;	// oauth 인증으로 넘어온 경우의 externalId 정보
    
    private final User user;
    
    public BlueskyUser(User user) {
    	this.user = user;
    	this.id = user.getId();
    	this.username = user.getUsername();
    	this.password = user.getPassword();
    	
    	List<String> authorityList = new ArrayList<>();
    	if (user.getUserAuthorityList() != null) {
    		for (UserAuthority userAuthority : user.getUserAuthorityList()) {
    			authorityList.add(userAuthority.getAuthority());
    		}
    	}
    	this.authorities =  AuthorityUtils.createAuthorityList(authorityList.stream().toArray(String[]::new));
    	this.accountNonExpired = user.isAccountNonExpired();
    	this.accountNonLocked = user.isAccountNonLocked();
    	this.credentialsNonExpired = user.isCredentialsNonExpired();
    	this.enabled = user.isEnabled();
    	this.userType = user.getUserType();
    	this.externalId = user.getExternalId();
    }
    
	@Override
	public void eraseCredentials() {
		this.password = null;
	}
}

