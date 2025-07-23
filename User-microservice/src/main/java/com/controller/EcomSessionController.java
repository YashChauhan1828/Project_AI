package com.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.UserEntity;
import com.Repository.EcomUserRepository;

import jakarta.servlet.http.HttpSession;

@RestController
public class EcomSessionController 
{
	@Autowired
	EcomUserRepository userdao;
	
	
	@GetMapping("/ehome")
	public ResponseEntity<?> homePage(HttpSession session) 
	{
		
		UserEntity user = (UserEntity) session.getAttribute("user");
		Map<String, Object> response = new HashMap<>();
		response.put("firstName", user.getFirst_name());
		response.put("profilePicPath", user.getProfile_picture_path());
		return ResponseEntity.ok(response);
	}
}
