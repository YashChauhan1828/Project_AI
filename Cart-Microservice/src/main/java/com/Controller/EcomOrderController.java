package com.Controller;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.EcomOrderitemDto;
import com.Entity.EcomCartEntity;
import com.Entity.EcomCartItemEntity;
import com.Entity.EcomOrderEntity;
import com.Entity.EcomOrderItemEntity;
import com.Repository.CartRepository;
import com.Repository.EcomCartRepository;
import com.Repository.EcomOrderItemRepository;
import com.Repository.EcomOrderRepository;
import jakarta.servlet.http.HttpSession;

@RestController
public class EcomOrderController 
{
	
	@Autowired
	EcomOrderRepository orderdao;
	
	@Autowired
	CartRepository cartdao;
	
	@Autowired
	EcomCartRepository cartitemdao;
	
	@Autowired
	EcomOrderItemRepository orderitemdao;
	
	@PostMapping("/orderhistory")
	public ResponseEntity<?> orderHistory(@RequestBody EcomOrderitemDto orderbean)
	{
		
		
		EcomCartEntity cart = cartdao.findByUserId(orderbean.getUserId());
		List<EcomCartItemEntity> cartitems = cartitemdao.findByCart(cart);
		
		EcomOrderEntity order = new EcomOrderEntity();
	        order.setUserId(orderbean.getUserId());
	        order.setOrderitems(new ArrayList<>());
	        
	        
	        for (EcomCartItemEntity cartItem1 : cartitems) {
	            EcomOrderItemEntity orderItem = new EcomOrderItemEntity();
	            orderItem.setOrder(order);
	            orderItem.setQty(cartItem1.getQty());
	            orderItem.setStatus("Pending");
	            orderItem.setOrderDate(LocalDateTime.now());
	            orderItem.setCategory(cartItem1.getCategory());
	            orderItem.setPrice(cartItem1.getPrice());
	            orderItem.setProductImagePath(cartItem1.getProductImagePath());
	            orderItem.setProductName(cartItem1.getProductName());
//	            orderitemdao.save(orderItem);
	           
	            order.getOrderitems().add(orderItem);
//	            orde
	            orderdao.save(order);
	        }
	        
	        cartitemdao.deleteAll(cartitems);
	      
	        List<EcomOrderEntity> products = orderdao.findByUserId(orderbean.getUserId());

	        List<EcomOrderitemDto> orderDtoList = new ArrayList<>();

	        for (EcomOrderEntity orders : products) {
	            for (EcomOrderItemEntity item : orders.getOrderitems()) {
	                EcomOrderitemDto dto = new EcomOrderitemDto();
	                dto.setUserId(orderbean.getUserId());
	                dto.setOrderItemId(item.getOrderItemId());
	                dto.setProductName(item.getProductName());
	                dto.setProductImagePath(item.getProductImagePath());
	                dto.setCategory(item.getCategory());
	                dto.setPrice(item.getPrice());
	                dto.setQty(item.getQty());
	                dto.setStatus(item.getStatus());
	                dto.setOrderDate(item.getOrderDate());
	                orderDtoList.add(dto);
	            }
	        }

	        Map<String, Object> response = new HashMap<>();
	        response.put("products", orderDtoList);
	        return ResponseEntity.ok(response);
//	        Map<String, Object> response = new HashMap<>();
//	        response.put("products", products);
	        
		
	}
	
	@GetMapping("/api/admin/allorders")
	public ResponseEntity<?> allOrders()
	{
		List<EcomOrderItemEntity> allorders = orderitemdao.findAll();
		List<EcomOrderitemDto> orders = allorders.stream().map(item->{
			EcomOrderitemDto dto = new EcomOrderitemDto();
			dto.setOrderItemId(item.getOrderItemId());
            dto.setProductName(item.getProductName());
            dto.setProductImagePath(item.getProductImagePath());
            dto.setCategory(item.getCategory());
            dto.setPrice(item.getPrice());
            dto.setQty(item.getQty());
            dto.setStatus(item.getStatus());
            dto.setOrderDate(item.getOrderDate());
            return dto;
		}).toList();
		
		return ResponseEntity.ok(orders);
	}
	
	@PutMapping("/api/admin/update/{orderItemId}")
	public ResponseEntity<?> updateStatus(@PathVariable("orderItemId") Integer orderItemId)
	{
		Optional<EcomOrderItemEntity> order = orderitemdao.findById(orderItemId);
		if(order.isEmpty())
		{
			   Map<String, Object> response = new HashMap<>();
		        response.put("success", false);
		        response.put("message", "Order not found");
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		else
		{
			EcomOrderItemEntity orders = order.get();
			orders.setStatus("Delivered");
			orderitemdao.save(orders);
			List<EcomOrderItemEntity> allorders = orderitemdao.findAll();
			List<EcomOrderitemDto> dtoList = allorders.stream().map(item->{
				EcomOrderitemDto dto = new EcomOrderitemDto();
				dto.setOrderItemId(item.getOrderItemId());
	            dto.setProductName(item.getProductName());
	            dto.setProductImagePath(item.getProductImagePath());
	            dto.setCategory(item.getCategory());
	            dto.setPrice(item.getPrice());
	            dto.setQty(item.getQty());
	            dto.setStatus(item.getStatus());
	            dto.setOrderDate(item.getOrderDate());
	            return dto;
			}).toList();
			
			return ResponseEntity.ok(dtoList);
		}
	}
}
