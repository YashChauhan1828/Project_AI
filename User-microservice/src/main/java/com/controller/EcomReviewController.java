package com.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.DTO.EcomReviewDTO;
import com.Entity.UserEntity;
import com.cloudinary.Cloudinary;
import jakarta.servlet.http.HttpSession;

@RestController
public class EcomReviewController 
{

    private final Cloudinary cloudinary;
	@Autowired
	WebClient.Builder webclientbuilder;

    EcomReviewController(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
	
	@PostMapping("/review/{productId}")
	public ResponseEntity<?> review(@PathVariable("productId") Integer productId,@RequestParam("userId") UUID userId,@RequestBody EcomReviewDTO reviewbean,HttpSession session)
	{
		UserEntity user = (UserEntity)session.getAttribute("user");
		String username = user.getFirst_name();
		EcomReviewDTO dto = new EcomReviewDTO();
		dto.setComment(reviewbean.getComment());
		dto.setProductId(productId);
		dto.setRating(reviewbean.getRating());
		dto.setUserId(userId);
		dto.setFirst_name(username);
		dto.setCreatedAt(LocalDateTime.now());
		
		String response = webclientbuilder.build()
				.post()
				.uri("http://ESHOP-PRODUCT/review/"+productId)
				.bodyValue(dto)
				.retrieve()
				.bodyToMono(String.class)
				.block();
				
		return ResponseEntity.ok(response);
	}
	
	public List<EcomReviewDTO> getAllReviews(Integer productId)
	{
		return webclientbuilder.build()
				.get()
				.uri("http://ESHOP-PRODUCT/api/public/reviews/"+productId)
				.retrieve()
				.bodyToFlux(EcomReviewDTO.class)
				.collectList()
				.block();
	}
	
	
	@GetMapping("/api/public/reviews/{productId}")
	public ResponseEntity<?> reviewList(@PathVariable("productId") Integer productId)
	{
		List<EcomReviewDTO> reviews = getAllReviews(productId);
		return ResponseEntity.ok(reviews);
	}
}
