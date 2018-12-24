package net.luversof.notification.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import net.luversof.notification.domain.Notification;


public interface NotificationRepository extends ReactiveMongoRepository<Notification, String> {

}
