package com.wisehub.platform.api.stream;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.wisehub.platform.data.model.dao.PlatformEventRepository;
import com.wisehub.platform.data.model.dao.UserRepository;

@Component
public class SaleAccountEventStreamService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PlatformEventRepository platformEventRepository;

	private ScheduledExecutorService executor = null;

	@Autowired
	private MessageSendingOperations<String> messagingTemplate;

	@Value("${generate.sale.account.event.fixed.seconds:2}")
	private Integer seconds;

	@Value("${generate.sale.account.event.amount:10}")
	private Integer amount;

	@Async
	public void produceSaleAccountEventData(Integer amountT, Integer secondsS) {
		if (executor == null) {
			executor = Executors.newSingleThreadScheduledExecutor();
		}		
		
		if (!executor.isTerminated()) {
			if (amountT != null && amountT != 0) {
				amount = amountT;
			}
			if (secondsS != null && secondsS != 0) {
				seconds = secondsS;
			}
			executor = Executors.newSingleThreadScheduledExecutor();
			try {
				CountDownLatch latch = new CountDownLatch(amount);
				SaleAccountEventStreamRunnable periodicTask = new SaleAccountEventStreamRunnable(latch, messagingTemplate);
				periodicTask.setPlatformEventRepository(platformEventRepository);
				periodicTask.setUserRepository(userRepository);
				executor.scheduleAtFixedRate(periodicTask, 0, seconds, TimeUnit.SECONDS);
				latch.await();
				System.out.println("Shutting down service...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			executor.shutdown();
		}
	}

}