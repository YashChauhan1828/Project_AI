package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.EcomUserDto;
import com.Entity.UserEntity;
import com.Repository.EcomUserRepository;

@RestController
@RequestMapping("/api/admin")
public class EcomAdminUserController 
{

	@Autowired
	EcomUserRepository userdao;
	
	@GetMapping("/allusers")
	public ResponseEntity<?> getAllUsers()
	{	
		List<UserEntity> users = userdao.findAll();
		List<EcomUserDto> dtoList = users.stream().map(user->{
			EcomUserDto dto = new EcomUserDto();
			dto.setEmail(user.getEmail());
			dto.setFirst_name(user.getFirst_name());
			dto.setPassword(user.getPassword());
			dto.setRole(user.getRole());
			dto.setUserId(user.getUser_id());
			return dto;
			
		}).toList();
		return ResponseEntity.ok(dtoList);
	}
	
	@PutMapping("/makeadmin/{userId}")
	public ResponseEntity<?> adminUsers(@PathVariable("userId") UUID userId)
	{
		Optional<UserEntity> user = userdao.findById(userId);
		
		if (user.isEmpty()) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("success", false);
	        response.put("message", "User not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
		else
		{
			UserEntity users = user.get();
		    users.setRole("Admin");
		    userdao.save(users);
			List<UserEntity> userss = userdao.findAll();
			List<EcomUserDto> dtoList = userss.stream().map(use->{
				EcomUserDto dto = new EcomUserDto();
				dto.setEmail(use.getEmail());
				dto.setFirst_name(use.getFirst_name());
				dto.setPassword(use.getPassword());
				dto.setRole(use.getRole());
				dto.setUserId(use.getUser_id());
				return dto;
				
			}).toList();
			return ResponseEntity.ok(dtoList);
		
		}
	}
	
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") UUID userId)
	{
		Optional<UserEntity> user = userdao.findById(userId);
		if (user.isEmpty()) 
		{
	        Map<String, Object> response = new HashMap<>();
	        response.put("success", false);
	        response.put("message", "User not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }
		else
		{
			UserEntity users = user.get();
		    userdao.delete(users);
			List<UserEntity> userss = userdao.findAll();
			List<EcomUserDto> dtoList = userss.stream().map(use->{
				EcomUserDto dto = new EcomUserDto();
				dto.setEmail(use.getEmail());
				dto.setFirst_name(use.getFirst_name());
				dto.setPassword(use.getPassword());
				dto.setRole(use.getRole());
				dto.setUserId(use.getUser_id());
				return dto;
				
			}).toList();
			return ResponseEntity.ok(dtoList);
		}
	}
}
