package com.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.EcomWishListDto;
import com.Entity.EcomWishCartItemEntity;
import com.Entity.EcomWishList;
import com.Repository.EcomWishItemRepository;
import com.Repository.EcomWishRepository;

@RestController
public class EcomWishCartController 
{
	@Autowired
	EcomWishRepository wishdao;
	
	@Autowired
	EcomWishItemRepository wishitemdao;
	
	@PostMapping("/addtowishcart")
	public String addToWishCart(@RequestBody EcomWishListDto wishcartitemdto)
	{
		EcomWishList wishcart = wishdao.findByUserId(wishcartitemdto.getUserId());
			if(wishcart==null)
			{
				wishcart = new EcomWishList();
				wishcart.setUserId(wishcartitemdto.getUserId());
				wishdao.save(wishcart);
			}
				EcomWishCartItemEntity wishitem = new EcomWishCartItemEntity();
				wishitem.setWishcart(wishcart);
				wishitem.setCategory(wishcartitemdto.getCategory());
				wishitem.setPrice(wishcartitemdto.getPrice());
				wishitem.setProductName(wishcartitemdto.getProductName());
				wishitem.setProductImagePath(wishcartitemdto.getProductImagePath());
				wishitemdao.save(wishitem); 
			
			return "Success";
		
	}
//	
	@GetMapping("/mywishcart")
	public ResponseEntity<?> wishCart(@RequestParam("userId") UUID userId)
	{
		Map<String, Object> response = new HashMap<>();
		if (userId == null)
		{
			response.put("sucess", false);
			response.put("message", "Email Already Exists");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		} 
		else 
		{
			EcomWishList wishcart = wishdao.findByUserId(userId);
			List<EcomWishCartItemEntity> products = wishitemdao.findByWishcart(wishcart);
			List<EcomWishListDto> dtoList = products.stream().map(item->{
				EcomWishListDto dto = new EcomWishListDto();
				dto.setUserId(userId);
				dto.setWishcartitemId(item.getWishcartitemId());
				dto.setProductImagePath(item.getProductImagePath());
				dto.setCategory(item.getCategory());
				dto.setPrice(item.getPrice());
				dto.setProductName(item.getProductName());
				return dto;
			}).toList();

			return ResponseEntity.ok(dtoList);
		}
	}
		
	@DeleteMapping("/removewishcartitem/{wishcartitemId}")
	public ResponseEntity<?> removeWishCartItem(@PathVariable("wishcartitemId") Integer cartId, @RequestParam("userId") UUID userId) 
	{
		wishitemdao.deleteById(cartId);
		Map<String, Object> response = new HashMap<>();
		if (userId == null) 
		{
			response.put("sucess", false);
			response.put("message", "Email Already Exists");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		} 
		else 
		{
			EcomWishList wishcart = wishdao.findByUserId(userId);
			List<EcomWishCartItemEntity> products = wishitemdao.findByWishcart(wishcart);
			List<EcomWishListDto> dtoList = products.stream().map(item->{
				EcomWishListDto dto = new EcomWishListDto();
				dto.setUserId(userId);
				dto.setWishcartitemId(item.getWishcartitemId());
				dto.setProductImagePath(item.getProductImagePath());
				dto.setCategory(item.getCategory());
				dto.setPrice(item.getPrice());
				dto.setProductName(item.getProductName());
				return dto;
			}).toList();

			return ResponseEntity.ok(dtoList);
		}
	}
	
}
