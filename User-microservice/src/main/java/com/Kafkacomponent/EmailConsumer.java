package com.Kafkacomponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.DTO.EcomUserRegisteredEventDto;
import com.service.EmailService;

@Component
public class EmailConsumer 
{
	@Autowired
	EmailService emailservice;
	
	@KafkaListener(topics = "user-registered" , groupId = "email-group")
	public void sendWelcomeMail(EcomUserRegisteredEventDto event)
	{
		System.out.println(event.getEmail());
		System.out.println(event.getFirst_name());
		emailservice.sendDemoMail(event.getEmail(), "Welcome to Ecomm App");
	}
}
