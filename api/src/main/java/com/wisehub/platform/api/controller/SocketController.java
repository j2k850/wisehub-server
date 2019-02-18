package com.wisehub.platform.api.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.wisehub.platform.api.stream.SaleAccountEventStreamService;
import com.wisehub.platform.api.view.model.Message;

@Controller
public class SocketController {

	private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

	@Autowired
	private SaleAccountEventStreamService saleAccountEventStreamService;
	 
	
    @MessageMapping("/hello")
    @SendTo("/topic/saleEvent")
	public Message send(Message message) throws Exception {
		String time = new SimpleDateFormat("HH:mm").format(new Date());
		saleAccountEventStreamService.produceSaleAccountEventData(message.getAmount(),message.getSeconds());
		
		return new Message(time + " : Hello, " + message.getContent() + "!");
	}

}