package com.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Dto.EcomProductDTO;
import com.Dto.EcomReviewDTO;
import com.entity.EcomProductEntity;
import com.entity.EcomReviewEntity;
//import com.entity.UserEntity;
import com.repository.EcomProductRepository;
import com.repository.EcomReviewRepository;

import jakarta.servlet.http.HttpSession;

@RestController
public class EcomReviewController 
{

	@Autowired
	EcomProductRepository productdao;
	
	@Autowired
	EcomReviewRepository reviewdao;
	
	@PostMapping("/review/{productId}")
	public ResponseEntity<?> reviewProduct(@PathVariable("productId") Integer productId,@RequestBody EcomReviewDTO reviewbean)
	{
		Map<String, Object> response = new HashMap<>();
		UUID user = reviewbean.getUserId();
		if(user==null)
		{
			response.put("sucess", false);
			response.put("message", "Please Login");
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
		}
		else
		{
			EcomProductEntity product = productdao.findById(productId).orElseThrow();
			EcomReviewEntity review = new EcomReviewEntity();
			review.setComment(reviewbean.getComment());
			review.setCreatedAt(reviewbean.getCreatedAt());
			review.setProduct(product);
			review.setRating(reviewbean.getRating());
			review.setUserId(user);
			review.setFirst_name(reviewbean.getFirst_name());
			reviewdao.save(review);
			return ResponseEntity.ok("Review added");
		}
	}
	
	@GetMapping("/api/public/reviews/{productId}")
	public ResponseEntity<?> getReviews(@PathVariable("productId") Integer productId)
	{
		EcomProductEntity product = productdao.findById(productId).orElseThrow();
		List<EcomReviewEntity> reviews = reviewdao.findByProduct(product);
		List<EcomReviewDTO> dtoList = reviews.stream().map(item->{
			EcomReviewDTO dto = new EcomReviewDTO();
			dto.setFirst_name(item.getFirst_name());
			dto.setComment(item.getComment());
			dto.setCreatedAt(item.getCreatedAt());
			dto.setProductId(productId);
			dto.setRating(item.getRating());
			return dto;			
		}).toList();
		
		return ResponseEntity.ok(dtoList);
	}

}