package net.luversof.security.oauth2.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.util.Assert;

import net.luversof.user.domain.User;
import net.luversof.user.domain.UserType;
import net.luversof.user.service.UserService;
import reactor.core.publisher.Mono;

public class BlueskyReactiveOAuth2AuthorizedClientService implements ReactiveOAuth2AuthorizedClientService {
	
	@Autowired
	private UserService userService;
	
	private final Map<String, OAuth2AuthorizedClient> authorizedClients = new ConcurrentHashMap<>();
	private final ClientRegistrationRepository clientRegistrationRepository;
	
	public BlueskyReactiveOAuth2AuthorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
		Assert.notNull(clientRegistrationRepository, "clientRegistrationRepository cannot be null");
		this.clientRegistrationRepository = clientRegistrationRepository;
	}

	@Override
	public <T extends OAuth2AuthorizedClient> Mono<T> loadAuthorizedClient(String clientRegistrationId, String principalName) {
		Mono<User> userMono = userService.findByExternalIdAndUserType(principalName, UserType.findByName(clientRegistrationId));
		if (userMono == null) {
			return null;
		}
		
		ClientRegistration registration = this.clientRegistrationRepository.findByRegistrationId(clientRegistrationId);
		if (registration == null) {
			return null;
		}
		return null;
	}

	@Override
	public Mono<Void> saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> removeAuthorizedClient(String clientRegistrationId, String principalName) {
		// TODO Auto-generated method stub
		return null;
	}

}
