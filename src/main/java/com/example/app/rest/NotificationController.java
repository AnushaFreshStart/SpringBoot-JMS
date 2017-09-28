package com.example.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.entity.MessageToSend;
import com.example.app.service.PublishMessageService;
import com.example.app.service.SubscribeMessageService;

@RestController
@RequestMapping("/customer")
public class NotificationController {
	
	@Autowired
	private PublishMessageService publishMessageService;
	
	@Autowired
	private SubscribeMessageService subscribeMessageService;
	
	@RequestMapping(value="/publishMessage", method=RequestMethod.POST)
	public String publishMessage() throws Exception
	{
		return publishMessageService.publishMessage();
	}
	
	@RequestMapping(value="/subscribeMessage", method=RequestMethod.GET)
	public String subscribeMessage() throws Exception
	{
		return subscribeMessageService.subscribeMessage();
	}
	
	

}
