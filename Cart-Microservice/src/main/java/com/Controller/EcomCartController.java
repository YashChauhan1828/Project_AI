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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.EcomCartItemDto;
import com.Entity.EcomCartEntity;
import com.Entity.EcomCartItemEntity;
import com.Repository.CartRepository;
import com.Repository.EcomCartRepository;

@RestController
public class EcomCartController 
{
	@Autowired
	CartRepository cartdao;

	@Autowired
	EcomCartRepository cartitemdao;

	@PostMapping("/addtocart")
	public String addToCart(@RequestBody EcomCartItemDto cartitemdto) 
	{
		EcomCartEntity cart = cartdao.findByUserId(cartitemdto.getUserId());
		if (cart == null) 
		{

			cart = new EcomCartEntity();
			cart.setUserId(cartitemdto.getUserId());
			cartdao.save(cart);
		}
		EcomCartItemEntity cartitem = new EcomCartItemEntity();
		cartitem.setCart(cart);
		cartitem.setQty(1);
		cartitem.setProductName(cartitemdto.getProductName());
		cartitem.setCategory(cartitemdto.getCategory());
		cartitem.setPrice(cartitemdto.getPrice());
		cartitem.setProductImagePath(cartitemdto.getProductImagePath());
		cartitemdao.save(cartitem);
		return "Success";
	}

	@GetMapping("/mycart")
	public ResponseEntity<?> myCart(@RequestParam("userId") UUID userId) 
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
			EcomCartEntity cart = cartdao.findByUserId(userId);
			List<EcomCartItemEntity> cartItems = cartitemdao.findByCart(cart);
			List<EcomCartItemDto> dtoList = cartItems.stream().map(item -> {
				EcomCartItemDto dto = new EcomCartItemDto();
				dto.setUserId(userId);
				dto.setCartitemId(item.getCartitemId());
				dto.setProductName(item.getProductName());
				dto.setProductImagePath(item.getProductImagePath());
				dto.setPrice(item.getPrice());
				dto.setQty(item.getQty());
				dto.setCategory(item.getCategory()); // if you store it
				return dto;
			}).toList();

			return ResponseEntity.ok(dtoList);
		}

	}

	@DeleteMapping("/removecartitem/{cartitemId}")
	public ResponseEntity<?> removeCartItem(@PathVariable("cartitemId") Integer cartId,
			@RequestParam("userId") UUID userId) 
	{
		cartitemdao.deleteById(cartId);
		Map<String, Object> response = new HashMap<>();
		if (userId == null) 
		{
			response.put("sucess", false);
			response.put("message", "Email Already Exists");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		} 
		else 
		{
			EcomCartEntity cart = cartdao.findByUserId(userId);
			List<EcomCartItemEntity> products = cartitemdao.findByCart(cart);
			List<EcomCartItemDto> dtoList = products.stream().map(item -> {
				EcomCartItemDto dto = new EcomCartItemDto();
				dto.setUserId(userId);
				dto.setCartitemId(item.getCartitemId());
				dto.setProductName(item.getProductName());
				dto.setProductImagePath(item.getProductImagePath());
				dto.setPrice(item.getPrice());
				dto.setQty(item.getQty());
				dto.setCategory(item.getCategory());
				return dto;
			}).toList();

			return ResponseEntity.ok(dtoList);
		}
	}

	@PutMapping("/plusqty/{cartitemId}")
	public ResponseEntity<?> plusQty(@PathVariable("cartitemId") Integer cartitemId,
			@RequestParam("userId") UUID userId) 
	{
		EcomCartItemEntity cartitem = cartitemdao.findByCartitemId(cartitemId);
		cartitem.setQty(cartitem.getQty() + 1);
		cartitemdao.save(cartitem);
		Map<String, Object> response = new HashMap<>();
		if (userId == null) 
		{
			response.put("sucess", false);
			response.put("message", "Email Already Exists");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		} 
		else 
		{
			EcomCartEntity cart = cartdao.findByUserId(userId);
			List<EcomCartItemEntity> products = cartitemdao.findByCart(cart);
			List<EcomCartItemDto> dtoList = products.stream().map(item -> {
				EcomCartItemDto dto = new EcomCartItemDto();
				dto.setUserId(userId);
				dto.setCartitemId(item.getCartitemId());
				dto.setProductName(item.getProductName());
				dto.setProductImagePath(item.getProductImagePath());
				dto.setPrice(item.getPrice());
				dto.setQty(item.getQty());
				dto.setCategory(item.getCategory());
				return dto;
			}).toList();

			return ResponseEntity.ok(dtoList);
		}
	}

	@PutMapping("/minusqty/{cartitemId}")
	public ResponseEntity<?> minusQty(@PathVariable("cartitemId") Integer cartitemId,
			@RequestParam("userId") UUID userId) 
	{
		EcomCartItemEntity cartitem = cartitemdao.findByCartitemId(cartitemId);
		Map<String, Object> response = new HashMap<>();
		if (cartitem.getQty() != 1) 
		{
			cartitem.setQty(cartitem.getQty() - 1);
			cartitemdao.save(cartitem);

			if (userId == null) 
			{
				response.put("sucess", false);
				response.put("message", "Email Already Exists");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			} 
			else 
			{
				EcomCartEntity cart = cartdao.findByUserId(userId);
				List<EcomCartItemEntity> products = cartitemdao.findByCart(cart);
				List<EcomCartItemDto> dtoList = products.stream().map(item -> {
					EcomCartItemDto dto = new EcomCartItemDto();
					dto.setUserId(userId);
					dto.setCartitemId(item.getCartitemId());
					dto.setProductName(item.getProductName());
					dto.setProductImagePath(item.getProductImagePath());
					dto.setPrice(item.getPrice());
					dto.setQty(item.getQty());
					dto.setCategory(item.getCategory());
					return dto;
				}).toList();
				return ResponseEntity.ok(dtoList);
			}
		} 
		else 
		{
			cartitemdao.deleteById(cartitemId);
			if (userId == null) 
			{
				response.put("sucess", false);
				response.put("message", "Email Already Exists");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			} 
			else 
			{
				EcomCartEntity cart = cartdao.findByUserId(userId);
				List<EcomCartItemEntity> products = cartitemdao.findByCart(cart);
				List<EcomCartItemDto> dtoList = products.stream().map(item -> {
					EcomCartItemDto dto = new EcomCartItemDto();
					dto.setUserId(userId);
					dto.setCartitemId(item.getCartitemId());
					dto.setProductName(item.getProductName());
					dto.setProductImagePath(item.getProductImagePath());
					dto.setPrice(item.getPrice());
					dto.setQty(item.getQty());
					dto.setCategory(item.getCategory());
					return dto;
				}).toList();
				return ResponseEntity.ok(dtoList);
			}
		}

	}
}
