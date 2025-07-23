package com.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import com.service.Paymentservice;

import com.Dto.EcomPaymentDto;
import com.service.Paymentservice;

import jakarta.servlet.http.HttpSession;
import net.authorize.api.contract.v1.ANetApiResponse;
import net.authorize.api.contract.v1.CreateTransactionResponse;
import net.authorize.api.contract.v1.MessageTypeEnum;

@RestController
public class EcomPaymentController 
{
	@Autowired
	Paymentservice paymentservice; 
		
	@PostMapping("/epayment")
	public ResponseEntity<String> epayment(@RequestBody EcomPaymentDto paymentbean) {
	    try {
	        ANetApiResponse response = paymentservice.run(paymentbean, paymentbean.getEmail());

	        if (response instanceof CreateTransactionResponse) {
	            CreateTransactionResponse res = (CreateTransactionResponse) response;

	            if (res.getMessages().getResultCode() == MessageTypeEnum.OK &&
	                res.getTransactionResponse() != null &&
	                res.getTransactionResponse().getTransId() != null) {

	                return ResponseEntity.ok("✅ Payment successful! Transaction ID: " + res.getTransactionResponse().getTransId());
	            } else {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Payment failed: " + res.getMessages().getMessage().get(0).getText());
	            }
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Unexpected response from payment gateway.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Internal server error: " + e.getMessage());
	    }
	}

}
