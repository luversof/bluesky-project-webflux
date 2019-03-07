package net.luversof.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import net.luversof.test.GeneralTest;
import net.luversof.user.domain.User;
import net.luversof.user.repository.UserRepository;
import net.luversof.user.service.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class UserTest extends GeneralTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void addUserTest() {
		Mono<User> addUser = userService.addUser("testUser", "{noop}testPassword");
		log.debug("result: {}", addUser.block());
	}

	@Test
	public void test() {
		Flux<User> findAll = userRepository.findAll();
		log.debug("result : {}", findAll.collectList().block());
	}
	
	
	@Test
	public void test2() {
		Mono<User> userMono = userService.findByUsername("test")
		.log()
		.switchIfEmpty(Mono.empty())
		.flatMap(user -> {
			log.debug("test!!! : {} ", user);
			return Mono.empty();
		});
		log.debug("block : {}", userMono.block());
	}
}
