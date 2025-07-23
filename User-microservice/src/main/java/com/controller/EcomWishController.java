package com.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.DTO.EcomProductDTO;
import com.DTO.EcomWishListDto;
import com.Entity.UserEntity;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class EcomWishController 
{
	@Autowired
	WebClient.Builder webClientbuilder;

	public EcomProductDTO getProductbyId(Integer productId)
	{
		return webClientbuilder.build()
				.get()
				.uri("http://ESHOP-PRODUCT/api/public/products/"+productId)
				.retrieve()
				.bodyToMono(EcomProductDTO.class)
				.block();
	}
	
	public EcomWishListDto builtWishItemRequest(Integer productId,UUID userId)
	{
		EcomProductDTO product = getProductbyId(productId);
		EcomWishListDto dto = new EcomWishListDto();
		dto.setUserId(userId);
		dto.setProductId(productId);
		dto.setProductName(product.getProductName());
		dto.setCategory(product.getCategory());
		dto.setPrice(product.getPrice());
		dto.setProductImagePath(product.getProductImagePath());
		return dto;
	}
	
	public String addToWishCart(EcomWishListDto wishitemDTO)
	{
		return webClientbuilder.build()
				.post()
				.uri("http://ESHOP-CART/addtowishcart")
				.bodyValue(wishitemDTO)
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}

	@PostMapping("/addtowishcart/{productId}")
	public ResponseEntity<?> addToWishcart(@PathVariable("productId") Integer productId,HttpSession session)
	{
		UserEntity user =(UserEntity) session.getAttribute("user");
		UUID userID = user.getUser_id();
		EcomWishListDto wistcartdto = builtWishItemRequest(productId, userID);
		String response = addToWishCart(wistcartdto);
		return ResponseEntity.ok(response);
	}
	
	public List<EcomWishListDto> myWishCart(UUID userId)
	{
		return webClientbuilder.build()
				.get()
				.uri(uriBuilder->uriBuilder
						.scheme("http")
						.host("ESHOP-CART")
						
						.path("/mywishcart")
						.queryParam("userId",userId)
						.build()
						)
				.retrieve()
				.bodyToFlux(EcomWishListDto.class)
				.collectList()
				.block();
	}
	
	@GetMapping("/mywishcart")
	public ResponseEntity<?> wishCartProducts(@RequestParam("userId") UUID userId)
	{
		List<EcomWishListDto> wishItems = myWishCart(userId);
		return ResponseEntity.ok(wishItems);
	}
	
	public List<EcomWishListDto> removeWishItem(Integer wishcartitemId , UUID userId)
	{
		return webClientbuilder.build()
				.delete()
				.uri(uriBuilder->uriBuilder
						.scheme("http")
						.host("ESHOP-CART")
						
						.path("/removewishcartitem/{wishcartitemId}")
						.queryParam("userId", userId)
						.build(wishcartitemId))
				.retrieve()
				.bodyToFlux(EcomWishListDto.class)
				.collectList()
				.block();				
	}
	
	@DeleteMapping("/removewishcartitem/{wishcartitemId}")
	public ResponseEntity<?> deleteitem(@PathVariable("wishcartitemId") Integer wishcartitemId,@RequestParam("userId") UUID userId)
	{
		List<EcomWishListDto> updatedcart = removeWishItem(wishcartitemId, userId);
		return ResponseEntity.ok(updatedcart);
	}
}
