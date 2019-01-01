package net.luversof.security.oauth2.client;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import net.luversof.user.domain.User;
import net.luversof.user.domain.UserType;
import net.luversof.user.service.UserService;
import reactor.core.publisher.Mono;

/**
 * 임시로 사용하므로 inmemory 형태로 사용함
 * @author luver
 *
 */
@Service
public class BlueskyReactiveOAuth2AuthorizedClientService implements ReactiveOAuth2AuthorizedClientService {
	
	@Autowired
	private UserService userService;
	
	private final Map<String, OAuth2AuthorizedClient> authorizedClients = new ConcurrentHashMap<>();
	private final ReactiveClientRegistrationRepository clientRegistrationRepository;
	
	public BlueskyReactiveOAuth2AuthorizedClientService(ReactiveClientRegistrationRepository reactiveClientRegistrationRepository) {
		Assert.notNull(reactiveClientRegistrationRepository, "reactiveClientRegistrationRepository cannot be null");
		this.clientRegistrationRepository = reactiveClientRegistrationRepository;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends OAuth2AuthorizedClient> Mono<T> loadAuthorizedClient(String clientRegistrationId, String principalName) {
		Assert.hasText(clientRegistrationId, "clientRegistrationId cannot be empty");
		Assert.hasText(principalName, "principalName cannot be empty");
		return (Mono<T>) getIdentifier(clientRegistrationId, principalName)
				.flatMap(identifier -> Mono.justOrEmpty(this.authorizedClients.get(identifier)));
	}

	@Override
	public Mono<Void> saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
		Assert.notNull(authorizedClient, "authorizedClient cannot be null");
		Assert.notNull(principal, "principal cannot be null");
		// db에 없으면 add하는 처리 추가
		Mono<User> userMono = userService.findByExternalIdAndUserType(principal.getName(), UserType.findByName(authorizedClient.getClientRegistration().getRegistrationId()));
		userMono.subscribe(user -> {
			List<String> authorityList = principal.getAuthorities().stream().map(grantedAuthority -> grantedAuthority.getAuthority()).collect(Collectors.toList());
			userService.addUser(principal.getName(), UserType.findByName(authorizedClient.getClientRegistration().getRegistrationId()), principal.getName(), authorityList);
		});
		
		return Mono.fromRunnable(() -> {
			String identifier = this.getIdentifier(authorizedClient.getClientRegistration(), principal.getName());
			this.authorizedClients.put(identifier, authorizedClient);
		});
	}

	@Override
	public Mono<Void> removeAuthorizedClient(String clientRegistrationId, String principalName) {
		Assert.hasText(clientRegistrationId, "clientRegistrationId cannot be empty");
		Assert.hasText(principalName, "principalName cannot be empty");
		return this.getIdentifier(clientRegistrationId, principalName)
				.doOnNext(identifier -> this.authorizedClients.remove(identifier))
				.then(Mono.empty());
	}
	
	
	private Mono<String> getIdentifier(String clientRegistrationId, String principalName) {
		return this.clientRegistrationRepository.findByRegistrationId(clientRegistrationId)
				.map(registration -> getIdentifier(registration, principalName));
	}

	private String getIdentifier(ClientRegistration registration, String principalName) {
		String identifier = "[" + registration.getRegistrationId() + "][" + principalName + "]";
		return Base64.getEncoder().encodeToString(identifier.getBytes());
	}
}