package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Entity.UserEntity;
import com.Repository.EcomUserRepository;
import com.service.EmailService;
import com.service.TokenService;

import jakarta.servlet.http.HttpSession;

@RestController
public class EcomMailVerficationController 
{
	@Autowired
	EmailService emailservice;
	
	@Autowired
	TokenService tokenservice;
	
	@Autowired
	EcomUserRepository userdao;
	
	Map<String, String> emailOtpMap = new HashMap<>();
	
	@PostMapping("/sendmail")
	public String sendMail(@RequestParam("email") String email)
	{
		String otp = tokenservice.otp();
		System.out.println(otp);
		emailOtpMap.put(email, otp);
		emailservice.sendDemoMail(email, "HI welcome to ABCD\nYour EmailVerification OTP is: "+otp);
		return otp;
	}
	
	@PostMapping("/verifyotp")
	 public String verifyOtp(@RequestParam String email, @RequestParam String otp,HttpSession session) 
	{
//		UserEntity userbean = (UserEntity)session.getAttribute("user");
        String storedOtp = emailOtpMap.get(email);
        System.out.println(email);
        if (storedOtp != null && storedOtp.equals(otp)) 
        {
            UserEntity user = userdao.findByEmail(email);
            if (user != null) 
            {
                user.setEmailVerified(true);  // You must have this field
                userdao.save(user);
                emailOtpMap.remove(email);  // Clear OTP
                return "Email Verified";
            }
        }
        return "Invalid OTP";
    }
}
