package com.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import com.Config.WebClientConfig;
import com.DTO.EcomCartItemDto;
import com.DTO.EcomProductDTO;
import com.Entity.UserEntity;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController

public class EcomCartController 
{


	@Autowired
	WebClient.Builder webClientbuilder;

    

	public EcomProductDTO getProductById(Integer productId)
	{
		return webClientbuilder.build()
				.get()
				.uri("http://ESHOP-PRODUCT/api/public/products/"+productId)
				.retrieve()
				.bodyToMono(EcomProductDTO.class)
				.block();
	}
	
	
	
	public EcomCartItemDto  buildCartItemRequest(Integer productId, UUID userId) {
	    EcomProductDTO product = getProductById(productId);

	    EcomCartItemDto dto = new EcomCartItemDto();
	    dto.setUserId(userId);
	    dto.setProductId(product.getProductId());
	    dto.setProductName(product.getProductName());
	    dto.setCategory(product.getCategory());
	    dto.setPrice(product.getPrice());
	    dto.setQty(1); // default quantity
	    dto.setProductImagePath(product.getProductImagePath());

	    return dto;
	}
	public String addToCart(EcomCartItemDto cartItemDTO) {
	    return webClientbuilder.build()
	        .post()
	        .uri("http://ESHOP-CART/addtocart")
	        .bodyValue(cartItemDTO)
	        .retrieve()
	        .bodyToMono(String.class)
	        .block();
	}
	
	@PostMapping("/addtocart/{productId}")
	public ResponseEntity<?> addToCart(@PathVariable("productId") Integer productId,HttpSession session) 
	{
		UserEntity user = (UserEntity)session.getAttribute("user");
		UUID UserId = user.getUser_id();
		System.out.println(productId);
		System.out.println(UserId);
		EcomCartItemDto cartItemDto = buildCartItemRequest(productId, UserId);
		String response = addToCart(cartItemDto);

		return ResponseEntity.ok(response);
	}

	public List<EcomCartItemDto> myCart(UUID userId) {
	    return webClientbuilder.build()
	            .get()
	            .uri(uriBuilder -> uriBuilder
	                .scheme("http")
	                .host("ESHOP-CART")	            
	                .path("/mycart")
	                .queryParam("userId", userId)
	                .build())
	            .retrieve()
	            .bodyToFlux(EcomCartItemDto.class)
	            .collectList()
	            .block();
	}

	@GetMapping("/mycart")
	public ResponseEntity<?> cartProducts(@RequestParam UUID userId)
	{
		List<EcomCartItemDto> cartItems = myCart(userId);
		
		return ResponseEntity.ok(cartItems);
	}
	public List<EcomCartItemDto> removeCartItem(Integer cartitemId,UUID userId) {
	    return webClientbuilder.build()
	            .delete()
	            .uri(uriBuilder -> uriBuilder
	                .scheme("http")
	                .host("ESHOP-CART")
	                
	                .path("/removecartitem/{cartitemId}")
	                .queryParam("userId", userId)
	                .build(cartitemId))
	            .retrieve()
	            .bodyToFlux(EcomCartItemDto.class)
	            .collectList()
	            .block();
	}
	@DeleteMapping("/removecartitem/{cartitemId}")
	public ResponseEntity<?> deleteItemById(@PathVariable("cartitemId") Integer cartitemId,@RequestParam("userId") UUID userId)
	{
		List<EcomCartItemDto> updatedcart = removeCartItem(cartitemId, userId);
		return ResponseEntity.ok(updatedcart);
	}
	public List<EcomCartItemDto> plusqty(Integer cartitemId,UUID userId)
	{
		return webClientbuilder.build()
				.put()
				.uri(uriBuilder->uriBuilder
						.scheme("http")
						.host("ESHOP-CART")
						
						.path("//plusqty/{cartitemId}")
						.queryParam("userId", userId)
						.build(cartitemId))
				.retrieve()
				.bodyToFlux(EcomCartItemDto.class)
				.collectList()
				.block();
	}
	@PutMapping("/plusqty/{cartitemId}")
	public ResponseEntity<?> plusQty(@PathVariable("cartitemId") Integer cartitemId,@RequestParam("userId") UUID userId)
	{
		List<EcomCartItemDto> updatedcart = plusqty(cartitemId, userId);
		return ResponseEntity.ok(updatedcart);
	}
	public List<EcomCartItemDto> minusqty(Integer cartitemId,UUID userId)
	{
		return webClientbuilder.build()
				.put()
				.uri(uriBuilder->uriBuilder
						.scheme("http")
						.host("ESHOP-CART")
						
						.path("//minusqty/{cartitemId}")
						.queryParam("userId", userId)
						.build(cartitemId))
				.retrieve()
				.bodyToFlux(EcomCartItemDto.class)
				.collectList()
				.block();
	}
	@PutMapping("/minusqty/{cartitemId}")
	public ResponseEntity<?> minusQty(@PathVariable("cartitemId") Integer cartitemId,@RequestParam("userId") UUID userId)
	{
		List<EcomCartItemDto> updatedcart = minusqty(cartitemId, userId);
		return ResponseEntity.ok(updatedcart);
	}
}
