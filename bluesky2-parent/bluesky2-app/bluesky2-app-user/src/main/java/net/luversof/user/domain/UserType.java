package net.luversof.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UserType {
	LOCAL(new String[]{ "ROLE_USER" }),
	GITHUB(new String[]{ "ROLE_USER", "ROLE_USER_GITHUB" }), 
	FACEBOOK(new String[]{ "ROLE_USER", "ROLE_USER_FACEBOOK" }),
	GOOGLE(new String[]{ "ROLE_USER", "ROLE_USER_GOOGLE" }),
	BATTLENET(new String[]{ "ROLE_USER", "ROLE_USER_BATTLENET" });
	
	@Getter private String[] authorities;
	
	public static UserType findByName(String name) {
		for (UserType userType : UserType.values()) {
			if (userType.name().equalsIgnoreCase(name)) {
				return userType;
			}
		}
		return null;
	}
}
