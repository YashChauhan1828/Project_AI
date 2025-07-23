package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.DTO.EcomUserRegisteredEventDto;

@Service
public class UserKafkaProducer 
{
	@Autowired
	KafkaTemplate<String, EcomUserRegisteredEventDto> kafkatemplate;
	
	public void sendUserRegisteredEvent(EcomUserRegisteredEventDto event) 
	{
		kafkatemplate.send("user-registered",event);
		System.out.println(event.getEmail());
	}
}
