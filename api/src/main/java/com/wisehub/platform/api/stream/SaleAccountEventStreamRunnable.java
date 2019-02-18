package com.wisehub.platform.api.stream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.messaging.core.MessageSendingOperations;

import com.wisehub.platform.api.domain.model.SalesAccountEvent;
import com.wisehub.platform.api.view.model.SaleAccountEventViewModel;
import com.wisehub.platform.data.model.PlatformEvent;
import com.wisehub.platform.data.model.User;
import com.wisehub.platform.data.model.dao.DTOParameter;
import com.wisehub.platform.data.model.dao.PlatformEventRepository;
import com.wisehub.platform.data.model.dao.UserRepository;
import com.wisehub.platform.data.model.util.TimeUUID;
import com.wisehub.platform.data.model.util.UUIDToDate;

public class SaleAccountEventStreamRunnable implements Runnable {

	private UserRepository userRepository;

	private PlatformEventRepository platformEventRepository;

	private List<UUID> userIds;

	private MessageSendingOperations<String> messagingTemplate;
	
	private CountDownLatch latch;

	
	public void setMessagingTemplate(final MessageSendingOperations<String> messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}
	
	public void setPlatformEventRepository(PlatformEventRepository platformEventRepository) {
		this.platformEventRepository = platformEventRepository;
	}
	
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public SaleAccountEventStreamRunnable(CountDownLatch latch, final MessageSendingOperations<String> messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
		this.latch = latch;
	}

	public void run() {
		if (userIds == null){
			List<User> users = userRepository.findAll(new DTOParameter());
			userIds = new ArrayList<>();
			for (User user : users) {
				userIds.add(user.getId());
			}
		}
		
		User user = selectUser();

		DTOParameter parameter = new DTOParameter();
		parameter.setUserId(user.getId().toString());
		List<PlatformEvent> events = platformEventRepository.findAll(parameter);

		List<SalesAccountEvent> salesAccountEvents = new ArrayList<>();
		salesAccountEvents.addAll(SalesAccountEvent.NAME_LOOKUP.values());

		int choize = (events.size() + 1) % salesAccountEvents.size();

		SalesAccountEvent sae = salesAccountEvents.get(choize);

		PlatformEvent platformEvent = new PlatformEvent();
		platformEvent.setUserId(user.getId());
		platformEvent.setEvent(sae.getName());
		UUID timeUUID = TimeUUID.buildTimeUUID(new Date().getTime());
		platformEvent.setEventTimestamp(timeUUID);
		platformEvent.setMessage(sae.getName());

		platformEventRepository.save(platformEvent);

		SaleAccountEventViewModel converter = converterToSaleAccountEvent(platformEvent, user);
		if (converter != null) {
			this.messagingTemplate.convertAndSend("/topic/saleEvent", converter);
		}

        latch.countDown();
	}

	private User selectUser() {
		int idx = ThreadLocalRandom.current().nextInt(userIds.size());
		User user = userRepository.findById(userIds.get(idx));
		return user;
	}

	private SaleAccountEventViewModel converterToSaleAccountEvent(PlatformEvent p, User u) {
		SaleAccountEventViewModel saeVM = new SaleAccountEventViewModel();
		saeVM.setCustomer(u.getName() != null && u.getName().getFirstName() != null && u.getName().getLastName() != null
				? u.getName().getFirstName() + " " + u.getName().getLastName() : u.getEmail());
		saeVM.setEvent(p.getEvent());
		saeVM.setMessage(p.getMessage());
		long time = UUIDToDate.getTimeFromUUID(p.getEventTimestamp());
		saeVM.setDate(new Date(time));

		return saeVM;
	}


}