package net.luversof.web.notification.controller;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.luversof.notification.domain.Notification;
import net.luversof.notification.repository.NotificationRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	@Autowired
	private NotificationRepository notificationRepository;

	@GetMapping(value = "/getMessage", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Notification>  getMessage() {
		return notificationRepository.findAll().flatMap(notification -> {
			Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
			Flux<Notification> notificationFlux = Flux.fromStream(Stream.generate(() -> notification));
			return Flux.zip(interval, notificationFlux).map(Tuple2::getT2);
		});
	}
	
	
	@PostMapping
	public Mono<Notification> save(Notification notification) {
		return notificationRepository.save(notification);
	}
}
