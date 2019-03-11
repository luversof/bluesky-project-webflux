package net.luversof.notification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import lombok.extern.slf4j.Slf4j;
import net.luversof.notification.domain.Notification;
import net.luversof.notification.repository.NotificationRepository;
import net.luversof.test.GeneralTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class NotificationTest extends GeneralTest {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Test
	public void test() {
		
		Notification notification = new Notification();
		notification.setMessage("test");
		
		Mono<Notification> save = notificationRepository.save(notification);
		
		
		log.debug("test : {}", save.block());
		
		ObjectId id = notification.getId();
		
		log.debug("test : {}", id.getCounter());
		log.debug("test : {}", id.getDate());
		log.debug("test : {}", id.getTimestamp());
		
	}
	
	@Test
	public void testBulkInsert() {
		
		List<Notification> notificationList = new ArrayList<>();
		for (int i = 0 ; i < 10 ; i++) {
			Notification notification = new Notification();
			notification.setUserId("userId" + i);
			notification.setMessage("test" + i);
			notificationList.add(notification);
		}
		
		Flux<Notification> saveAll = notificationRepository.saveAll(notificationList);
		log.debug("test : {}", saveAll.collectList().block());
	}
	
	
	@Test
	public void test2() {
		Mono<Notification> findById = notificationRepository.findById("5c2037e1bc93944278291fb0");
//		Mono<Notification> findById = notificationRepository.findById((long) 1234564);
		
		log.debug("result : {}", findById.block());
	}
	
	@Test
	public void test3() {
		Notification notification = new Notification();
		
		
		Flux<Notification> findAll = notificationRepository.findAll(Example.of(notification));
		
		log.debug("result : {}", findAll.collectList().block());
		
	}
	
	
	@Test
	public void test4() {
		Flux<Notification> findByCreatedDateBefore = notificationRepository.findByCreatedDateBefore(LocalDateTime.now());
		List<Notification> block = findByCreatedDateBefore.collectList().block();
		log.debug("result : {}", block);
		log.debug("result : {}", block.size());
	}
}
