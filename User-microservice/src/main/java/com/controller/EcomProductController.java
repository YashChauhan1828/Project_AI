package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.DTO.EcomProductDTO;

@RestController
@RequestMapping("/api/public")
public class EcomProductController 
{
	@Autowired
	WebClient.Builder webClientbuilder;
	
	public List<EcomProductDTO> getAllproducts()
	{
		return webClientbuilder.build()
			   .get()
			   .uri("http://ESHOP-PRODUCT/api/public/userproducts")
			   .retrieve()
			   .bodyToFlux(EcomProductDTO.class)
			   .collectList()
			   .block();	   	
	}
	
	@GetMapping("/userproducts")
	public ResponseEntity<?> getProducts()
	{
		List<EcomProductDTO> products = getAllproducts();
	
		return ResponseEntity.ok(products);
	}
	
	public EcomProductDTO getProductById(Integer productId)
	{
		return webClientbuilder.build()
				.get()
				.uri("http://ESHOP-PRODUCT/api/public/products/"+productId)
				.retrieve()
				.bodyToMono(EcomProductDTO.class)
				.block();
	}

	@GetMapping("/userproductview/{productId}")
	public ResponseEntity<?> getProduct(@PathVariable("productId") Integer productId)
	{
		EcomProductDTO product = getProductById(productId);
		return ResponseEntity.ok(product);
	}
	
	public List<EcomProductDTO> getAllproductsByQuery(String query)
	{
		return webClientbuilder.build()
			   .get()
			   .uri(uriBuilder -> uriBuilder
		                .scheme("http")
		                .host("ESHOP-PRODUCT")	            
		                .path("/api/public/search")
		                .queryParam("query",query)
		                .build())
			   .retrieve()
			   .bodyToFlux(EcomProductDTO.class)
			   .collectList()
			   .block();	   	
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> Search(@RequestParam("query") String query)
	{
		List<EcomProductDTO> products = getAllproductsByQuery(query);
		
		return ResponseEntity.ok(products);
	}
}
