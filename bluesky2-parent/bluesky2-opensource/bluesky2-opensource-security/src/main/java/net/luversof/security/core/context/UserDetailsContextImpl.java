package net.luversof.security.core.context;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
public class UserDetailsContextImpl implements UserDetailsContext {
	private UserDetails userDetails;
}
