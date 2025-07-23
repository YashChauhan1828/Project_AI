package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.dto.EcomOrderitemDto;

@RestController
@RequestMapping("/api/admin")
public class OrderManagementController 
{
	@Autowired
	WebClient.Builder webClientbuilder;
	
	public List<EcomOrderitemDto> allOrders()
	{
		return webClientbuilder.build()
				.get()
				.uri("http://ESHOP-CART/api/admin/allorders")
				.retrieve()
				.bodyToFlux(EcomOrderitemDto.class)
				.collectList()
				.block();
	}
	
	@GetMapping("/allorders")
	public ResponseEntity<?> allorders()
	{
		List<EcomOrderitemDto> orders = allOrders();
		return ResponseEntity.ok(orders);
	}

	public List<EcomOrderitemDto> update(Integer orderItemId)
	{
		return webClientbuilder.build()
				.put()
				.uri("http://ESHOP-CART/api/admin/update/"+orderItemId)
				.retrieve()
				.bodyToFlux(EcomOrderitemDto.class)
				.collectList()
				.block();
	}
	
	@PutMapping("/update/{orderItemId}")
	public ResponseEntity<?> updateStatus(@PathVariable("orderItemId") Integer orderItemId)
	{
		List<EcomOrderitemDto> updatedorders = update(orderItemId);
		return ResponseEntity.ok(updatedorders);
	}
}
