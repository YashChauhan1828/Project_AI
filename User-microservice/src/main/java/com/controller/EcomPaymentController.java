package com.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.DTO.EcomPaymentDto;
import com.Entity.EcomShippingEntity;
import com.Entity.UserEntity;
import com.Repository.EcomShippingRepository;
import com.Repository.EcomUserRepository;

@RestController
public class EcomPaymentController 
{

	@Autowired
	WebClient.Builder webClientBuilder;
	
	@Autowired
	EcomShippingRepository shippingdao;
	
	@Autowired
	EcomUserRepository userdao;
	
	public EcomPaymentDto payment(UUID userId)
	{
		EcomShippingEntity shipDetails=shippingdao.findLatestShippingForUser(userId);
		Optional<UserEntity> user = userdao.findById(userId);
		EcomPaymentDto dto = new EcomPaymentDto();
		dto.setUserId(userId);
		dto.setEmail(user.get().getEmail());
		dto.setRecipientName(shipDetails.getRecipientName());
		dto.setAddress(shipDetails.getAddress());
		dto.setCity(shipDetails.getCity());
		dto.setState(shipDetails.getState());
		dto.setCountry(shipDetails.getCountry());
		dto.setZipCode(shipDetails.getZipCode());
		return dto;
	}
	@PostMapping("/epayment")
	public ResponseEntity<String> triggerPayment(@RequestParam("userId") UUID userId, @RequestBody EcomPaymentDto paymentbean) {
	    try {
	        EcomPaymentDto dto = payment(userId);
	        dto.setCreditcardnumber(paymentbean.getCreditcardnumber());
	        dto.setCvv(paymentbean.getCvv());
	        dto.setDate(paymentbean.getDate());
	        dto.setPrice(paymentbean.getPrice());

	        String response = webClientBuilder.build()
	            .post()
	            .uri("http://ESHOP-PAYMENT/epayment")
	            .bodyValue(dto)
	            .retrieve()
	            .bodyToMono(String.class)
	            .block();

	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body("❌ Payment Failed: " + e.getMessage());
	    }
	}

}
