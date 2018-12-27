package net.luversof.notification.repository;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import net.luversof.notification.domain.Notification;
import reactor.core.publisher.Flux;


public interface NotificationRepository extends ReactiveMongoRepository<Notification, String> {

	Flux<Notification> findByCreatedDateBefore(LocalDateTime localDateTime);
	
	Flux<Notification> findByCreatedDateAfter(LocalDateTime localDateTime);
}
