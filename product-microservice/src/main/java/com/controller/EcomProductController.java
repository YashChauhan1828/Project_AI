package com.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.Dto.EcomOrderitemDto;
import com.Dto.EcomProductDTO;
import com.entity.EcomProductEntity;
import com.repository.EcomProductRepository;


import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/public")
public class EcomProductController 
{
	
	
	@Autowired
	EcomProductRepository productdao;
	
	
	@Cacheable("userProducts")
	@GetMapping("/userproducts")
	public List<EcomProductDTO> userProducts()
	{
		System.out.println("cache miss");
		List<EcomProductEntity> products = productdao.findTop9();
		return products.stream().map(item->{
			EcomProductDTO dto = new EcomProductDTO();
			dto.setProductId(item.getProductId());
			dto.setProductName(item.getProductName());
			dto.setCategory(item.getCategory());
			dto.setPrice(item.getPrice());
			dto.setProductImagePath(item.getProductImagePath());
			dto.setQty(item.getQty());
			return dto;
			
		}).toList();
		
		
	}
	
	@Cacheable(value = "productView" ,key = "#productId")
	@GetMapping("/userproductview/{productId}")
	public ResponseEntity<?> userProductView(@PathVariable("productId") Integer productId,EcomProductEntity productbean,Model model)
	{
		Optional<EcomProductEntity>	product=productdao.findById(productId);
		Map<String, Object> response = new HashMap<>();
		if(product == null)
		{
			response.put("sucess", false);
			response.put("message", "No Product Available");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
		else
		{
			EcomProductDTO dto = new EcomProductDTO();
			dto.setProductImagePath(product.get().getProductImagePath());
			dto.setCategory(product.get().getCategory());
			dto.setPrice(product.get().getPrice());
			dto.setProductName(product.get().getProductName());
			return ResponseEntity.ok(dto);
		}
	}
	
	@Cacheable(value = "productDetails", key = "#productId")
	@GetMapping("/products/{productId}")
	public EcomProductEntity productDetailsById(@PathVariable("productId") Integer productId) 
	{
		System.out.println("cache miss");
	    Optional<EcomProductEntity> product = productdao.findById(productId);
	    return product.get();
	}

	@Cacheable(value = "searchProducts", key = "#query")	
	@GetMapping("/search")
	public List<EcomProductDTO> Search(@RequestParam("query") String query)
	{
		
		List<EcomProductEntity> products =productdao.findByCategoryContainingIgnoreCaseOrProductNameContainingIgnoreCase(query.trim(), query.trim());
		
		
			List<EcomProductDTO> dtoList = products.stream().map(item->{
				EcomProductDTO dto = new EcomProductDTO();
				dto.setProductId(item.getProductId());
				dto.setProductName(item.getProductName());
				dto.setCategory(item.getCategory());
				dto.setPrice(item.getPrice());
				dto.setProductImagePath(item.getProductImagePath());
				dto.setQty(item.getQty());
				return dto;
				
			}).toList();
			
			return dtoList;
		}
	
	

}