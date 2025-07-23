package com.controller;

import java.io.IOException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpStatus;
//import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.netflix.discovery.DiscoveryClient;
import com.service.TokenService;
import com.service.UserKafkaProducer;
import com.DTO.EcomProductDTO;
import com.DTO.EcomUserDto;
import com.DTO.EcomUserRegisteredEventDto;
import com.Entity.UserEntity;
import com.Repository.EcomUserRepository;

import jakarta.annotation.PostConstruct;
//import com.service.TokenService;
//import com.service.fileUploadService;
//import com.utils.Validators;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
@RequestMapping("/api/public")
public class EcomUserController {

    
	

	@Autowired
	EcomUserRepository userdao;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	Cloudinary cloudinary;
//	
	@Autowired
	TokenService tokenservice;
	
	@Autowired
	UserKafkaProducer kafkaproducer;

	@PostMapping("/esignup")
	public ResponseEntity<?> signupPost(@RequestParam("first_name") String firstName,
			@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("profilePicture") MultipartFile profilePicture, Model model) 
	{
//		UserEntity userop = userdao.findByEmail(email);
//		if (userop!=null) {
			Map<String, Object> response = new HashMap<>();
//			response.put("sucess", false);
//			response.put("message", "Email Already Exists");
//			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
////			return ResponseEntity.ok(response);
//			//			return "Fail";
//		} else 
//		{
			String encryptedPassword = passwordEncoder.encode(password);

//		if(result.hasErrors())
//		{
//			model.addAttribute("result",result);
//			return "EcomSignUp";
//		}
//		else
//		{
			UserEntity user = new UserEntity();
			user.setFirst_name(firstName);
			user.setEmail(email);
			user.setRole("User");
			user.setPassword(encryptedPassword);

			try {
			Map result	= cloudinary.uploader().upload(profilePicture.getBytes(), ObjectUtils.emptyMap());
			user.setProfile_picture_path(result.get("url").toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userdao.save(user);
			
			EcomUserRegisteredEventDto event = new EcomUserRegisteredEventDto();
			event.setEmail(user.getEmail());
			event.setFirst_name(user.getFirst_name());
			kafkaproducer.sendUserRegisteredEvent(event);
			
			return ResponseEntity.ok(response);
//			return "Sucess";
//		}
		
	}

	@PostMapping("/elogin")
	public ResponseEntity<?> EcomLogin(@RequestBody UserEntity user, Model model, HttpSession session) 
	{

		UserEntity dbUser = userdao.findByEmail(user.getEmail());

		if (dbUser==null || !passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
			Map<String, Object> response = new HashMap<>();
			response.put("sucess", false);
			response.put("message", "Invalid Credentials");
			return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body(response);
		} 
		else 
		{
			String authToken = tokenservice.generateToken();
			dbUser.setToken(authToken);
			userdao.save(dbUser);
			EcomUserRegisteredEventDto event = new EcomUserRegisteredEventDto();
			event.setEmail(user.getEmail());
			event.setFirst_name(user.getFirst_name());
			kafkaproducer.sendUserRegisteredEvent(event);
			Map<String, Object> response = new HashMap<>();
			response.put("success", true);
			response.put("message", "Login successful");
			response.put("token", dbUser.getToken());
			response.put("userId", dbUser.getUser_id());
			response.put("email", dbUser.getEmail());
			return ResponseEntity.ok(response);
		}
	}

	
//	
	@PostMapping("/updatepassword")
	public String EupdatePassword(@RequestParam("email") String email, @RequestParam("password1") String password1,
			@RequestParam("password2") String password2, Model model) 
	{
		UserEntity user = userdao.findByEmail(email);
		if(password1.matches(password2))
		{
			String encryptedPassword1 = passwordEncoder.encode(password1);
			user.setPassword(encryptedPassword1);
		userdao.save(user);
		model.addAttribute("updatepassword", "Password Updated Sucessfully");
		return "EcomLogin";
		}
		else 
		{
			return "Fail";
		}
		}


//	@GetMapping("/logout")
//	public String logout(HttpSession session)
//	{
//		session.invalidate();
//		return "redirect:/elogin";
//	}
}
