package com.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.DTO.EcomOrderitemDto;

@RestController
public class EcomOrderController 
{
    @Autowired
    WebClient.Builder webClientBuilder;
    
    @GetMapping("/orderhistory")
    public ResponseEntity<?> orderHistory(@RequestParam("userId") UUID userId)
    {
        EcomOrderitemDto orderbean = new EcomOrderitemDto();
        orderbean.setUserId(userId);
        

        Map<String, List<EcomOrderitemDto>> response = webClientBuilder.build()
            .post()
            .uri("http://ESHOP-CART/orderhistory")
            .bodyValue(orderbean)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<Map<String, List<EcomOrderitemDto>>>() {})
            .block();

        return ResponseEntity.ok(response.get("products"));
    }
}
