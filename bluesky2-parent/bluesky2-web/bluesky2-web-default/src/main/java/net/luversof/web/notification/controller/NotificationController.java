package net.luversof.web.notification.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.luversof.notification.domain.Notification;
import net.luversof.notification.repository.NotificationRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	@Autowired
	private NotificationRepository notificationRepository;

	@GetMapping(value = "/getMessage", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Notification> getMessage() {
		return notificationRepository.findAll().flatMap(notification -> {
			Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
			Flux<Notification> notificationFlux = Flux.fromStream(Stream.generate(() -> notification));
			return Flux.zip(interval, notificationFlux).map(Tuple2::getT2);
		});
	}
	
	// 이거 계속 호출함. 위에꺼는 연결 끊으면 더이상 호출안하는데 차이가 무엇인지 확인 필요
	@GetMapping(value = "/getMessage2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Notification> getMessage2() {
		// 담아 쓸만한 객체는 뭐가 있을까?
		final Set<LocalDateTime> set = new HashSet<>();
		return Flux.interval(Duration.ofSeconds(1)).flatMap(sequence -> {
			if (set.isEmpty()) {
				set.add(LocalDateTime.now());
				log.debug("findAll");
				return notificationRepository.findAll();
			} else {
				LocalDateTime localDateTime = set.stream().findAny().get();
				log.debug("findByCreatedDateBefore");
				Flux<Notification> findByCreatedDateBefore = notificationRepository.findByCreatedDateAfter(localDateTime);
				set.clear();
				set.add(LocalDateTime.now());
				return findByCreatedDateBefore;
			}
		});
	}
	
	@PostMapping
	public Mono<Notification> save(@RequestBody Notification notification) {
		return notificationRepository.save(notification);
	}
}
