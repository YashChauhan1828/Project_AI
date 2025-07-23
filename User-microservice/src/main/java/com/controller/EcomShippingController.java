package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.EcomShippingEntity;
import com.Entity.UserEntity;
import com.Repository.EcomShippingRepository;
import com.Repository.EcomUserRepository;

import jakarta.servlet.http.HttpSession;

@RestController
public class EcomShippingController 
{
	@Autowired
	EcomShippingRepository shippingdao;
	
	@Autowired
	EcomUserRepository userdao;
	
	@PostMapping("/eshipping")
	public String Shipping(@RequestBody EcomShippingEntity shippingbean,HttpSession session , Model model)
	{
		UserEntity user = (UserEntity)session.getAttribute("user");
		shippingbean.setUser(user);
		shippingdao.save(shippingbean);
		return"sucess";
	}
}
