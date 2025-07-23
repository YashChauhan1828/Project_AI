package com.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


import com.dto.EcomUserDto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/admin")
public class UserManagementController 
{
	@Autowired
	WebClient.Builder webclientbuilder;
	
	public List<EcomUserDto> getAllUsers()
	{
		return webclientbuilder.build()
				.get()
				.uri("http://UNKNOWN/api/admin/allusers")
				.retrieve()
				.bodyToFlux(EcomUserDto.class)
				.collectList()
				.block();
	}
	@GetMapping("/allusers")
	public ResponseEntity<?> allUsers() 
	{
		List<EcomUserDto> users = getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	public List<EcomUserDto> Admin(UUID userId)
	{
		return webclientbuilder.build()
				.put()
				.uri("http://UNKNOWN/api/admin/makeadmin/"+userId)
				.retrieve()
				.bodyToFlux(EcomUserDto.class)
				.collectList()
				.block();
	}
	
	@PutMapping("/makeadmin/{userId}")
	public ResponseEntity<?> makeAdmin(@PathVariable("userId") UUID userId)
	{
		List<EcomUserDto> updatedusers = Admin(userId);
		return ResponseEntity.ok(updatedusers);
	}
	
	public List<EcomUserDto> deleteUser(UUID userId)
	{
		return webclientbuilder.build()
				.delete()
				.uri("http://UNKNOWN/api/admin/deleteuser/"+userId)
				.retrieve()
				.bodyToFlux(EcomUserDto.class)
				.collectList()
				.block();
	}
	
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<?> Delete(@PathVariable("userId") UUID userId)
	{
		List<EcomUserDto> updatedusers = deleteUser(userId);
		return ResponseEntity.ok(updatedusers);
	}
}
