package net.luversof.notification;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import net.luversof.notification.domain.Notification;
import net.luversof.notification.repository.NotificationRepository;
import net.luversof.test.GeneralTest;

@Slf4j
public class NotificationTest extends GeneralTest {
	
	@Autowired
	private NotificationRepository notificationRepository;

	@Test
	public void test() {
		
		Notification notification = new Notification();
		
		log.debug("test : {}", notificationRepository.findAll().blockFirst());
		
	}
}
