package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Config.CloudinaryConfig;
import com.Dto.EcomOrderitemDto;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.entity.EcomProductEntity;
import com.repository.EcomProductRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/admin")
public class EcomAdminController 
{

	@Autowired
	WebClient.Builder webClientBuilder;
//
	@Autowired
	Cloudinary cloudinary;

	@Autowired
	EcomProductRepository productdao;

	@PostMapping("/saveproduct")
	public String saveProduct(@RequestParam("productName") String productName,
			@RequestParam("category") String category, @RequestParam("price") Integer price,
			@RequestParam("qty") Integer qty, @RequestParam("productImage") MultipartFile productImage) 
	{
		Map result;
		try 
		{
			result = cloudinary.uploader().upload(productImage.getBytes(), ObjectUtils.emptyMap());
			EcomProductEntity productentity = new EcomProductEntity();
			productentity.setProductName(productName);
			productentity.setCategory(category);
			productentity.setQty(qty);
			productentity.setPrice(price);
			productentity.setProductImagePath(result.get("url").toString());
			productdao.save(productentity);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Success";
	}

	@GetMapping("/aproducts")
	public ResponseEntity<?> Products() 
	{
		List<EcomProductEntity> products = productdao.findAll();
		Map<String, Object> response = new HashMap<>();
		response.put("products", products);
		return ResponseEntity.ok(response);
	}

//	
	@GetMapping("/aviewproduct/{productId}")
	public ResponseEntity<?> viewProduct(@PathVariable("productId") Integer productId) 
	{
		Optional<EcomProductEntity> product = productdao.findById(productId);
//		if (product.isEmpty()) 
//		{
//			Map<String, Object> response = new HashMap<>();
//			response.put("sucess", false);
//			response.put("message", "No product found");
//			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
//		}
//		else 
//		{
			Map<String, Object> response = new HashMap<>();
			response.put("product", product);

			return ResponseEntity.ok(response);
		}
//	}

	@DeleteMapping("/deleteproduct/{productId}")
	public ResponseEntity<?> deleteProducts(@PathVariable("productId") Integer productId) 
	{
		productdao.deleteById(productId);
		List<EcomProductEntity> products = productdao.findAll();
		Map<String, Object> response = new HashMap<>();
		response.put("products", products);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/updateproduct/{productId}")
	public String updateProduct(@PathVariable("productId") Integer productId,
			@RequestParam("productName") String productName, @RequestParam("category") String category,
			@RequestParam("price") Integer price, @RequestParam("qty") Integer qty,
			@RequestParam("productImage") MultipartFile productImage, EcomProductEntity productentity) 
	{
		Map result;
		try 
		{
			result = cloudinary.uploader().upload(productImage.getBytes(), ObjectUtils.emptyMap());
			productentity.setProductName(productName);
			productentity.setCategory(category);
			productentity.setQty(qty);
			productentity.setPrice(price);
			productentity.setProductImagePath(result.get("url").toString());
			productdao.save(productentity);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	public List<EcomOrderitemDto> update()
	{
		return webClientBuilder.build()
				.get()
				.uri("http://ESHOP-CART/orderhistory")
				.retrieve()
				.bodyToFlux(EcomOrderitemDto.class)
				.collectList()
				.block();
	}

	@GetMapping("/orderhistory")
	public String updateQuantity()
	{
		List<EcomOrderitemDto> orders =update();
		for(EcomOrderitemDto product:orders)
		{
			productdao.reduceProductQuantity(product.getQty(), product.getProductId(), product.getQty());
		}
		return "Sucess";
	}
}
