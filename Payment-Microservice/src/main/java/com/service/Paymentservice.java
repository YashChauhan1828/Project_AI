package com.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Dto.EcomPaymentDto;

import jakarta.servlet.http.HttpSession;
import net.authorize.Environment;
import net.authorize.api.contract.v1.*;
import net.authorize.api.controller.CreateTransactionController;
import net.authorize.api.controller.base.ApiOperationBase;

@Service
public class Paymentservice {
    
	@Autowired
	HttpSession session;
	
//	@Autowired
//	TokenService tokenservice;
	
    public ANetApiResponse run(EcomPaymentDto paymentbean , String email  ) {
    	
    	String apiLoginId = "7b6SwXH2J";
    	String transactionKey = "44Cn4CY2Q5tb3vCM";
    	
        // Set the request to operate in either the sandbox or production environment
        ApiOperationBase.setEnvironment(Environment.SANDBOX);

        // Create object with merchant authentication details
        MerchantAuthenticationType merchantAuthenticationType  = new MerchantAuthenticationType() ;
        merchantAuthenticationType.setName(apiLoginId);
        merchantAuthenticationType.setTransactionKey(transactionKey);

        // Populate the payment data
        PaymentType paymentType = new PaymentType();
        CreditCardType creditCard = new CreditCardType();
        creditCard.setCardNumber(paymentbean.getCreditcardnumber());
        creditCard.setExpirationDate(paymentbean.getDate());
        paymentType.setCreditCard(creditCard);

        // Set email address (optional)
//        EcomShippingEntity shippingbean = (EcomShippingEntity) session.getAttribute("ship");
//		UserEntity user = (UserEntity)session.getAttribute("user");
        
		
//        customer.setId(userbean.getUserId());

        // Create the payment transaction object
        TransactionRequestType txnRequest = new TransactionRequestType();
        txnRequest.setTransactionType(TransactionTypeEnum.AUTH_CAPTURE_TRANSACTION.value());
        txnRequest.setPayment(paymentType);
        
        
        // Calculate base price and tax
        
        BigDecimal basePrice = new BigDecimal(paymentbean.getPrice());
        BigDecimal taxAmount = basePrice.multiply(new BigDecimal("0.18")).setScale(2, RoundingMode.CEILING);
        BigDecimal shipTaxAmount = basePrice.multiply(new BigDecimal("0.5")).setScale(2,RoundingMode.CEILING);
        BigDecimal totalAmount = basePrice.add(taxAmount).setScale(2, RoundingMode.CEILING);
        BigDecimal finalAmount = totalAmount.add(shipTaxAmount).setScale(2, RoundingMode.CEILING);

        // Set tax
        

//        CustomerAddressType address = new CustomerAddressType();
//		address.setFirstName(paymentbean.getFirst_name());
//		txnRequest.setBillTo(address);
        
		CustomerDataType customerDetails = new CustomerDataType();
		customerDetails.setId(paymentbean.getUserId().toString().replace("-", "").substring(0, 20));
		customerDetails.setEmail(paymentbean.getEmail());
		txnRequest.setCustomer(customerDetails);
		
//		OrderType order = new OrderType();
//		order.setInvoiceNumber(tokenservice.generateOrder());
//		order.setDescription("Thank you");
//		txnRequest.setOrder(order);
		
        NameAndAddressType customeraddress = new NameAndAddressType();
        customeraddress.setFirstName(paymentbean.getRecipientName());
        customeraddress.setAddress(paymentbean.getAddress());
        customeraddress.setCity(paymentbean.getCity());
        customeraddress.setState(paymentbean.getState());
        customeraddress.setCountry(paymentbean.getCountry());
        customeraddress.setZip(paymentbean.getZipCode());
     // After customeraddress is populated
        txnRequest.setShipTo(customeraddress);
        
        if("USA".equalsIgnoreCase(paymentbean.getCountry())||"UAE".equalsIgnoreCase(paymentbean.getCountry()))
        {
        	ExtendedAmountType tax = new ExtendedAmountType();
            tax.setAmount(taxAmount);
            tax.setName("GST");
            tax.setDescription("Government Tax");
            ExtendedAmountType shippingtax = new ExtendedAmountType();
            shippingtax.setAmount(shipTaxAmount);
            shippingtax.setName(" Standard Shipping");
            txnRequest.setShipping(shippingtax);
            txnRequest.setTax(tax);
            txnRequest.setAmount(finalAmount);
        }
        else
        {
        	ExtendedAmountType tax = new ExtendedAmountType();
        	tax.setAmount(taxAmount);
        	tax.setName("GST");
        	tax.setDescription("Government Tax");
        	txnRequest.setTax(tax);
        	txnRequest.setAmount(totalAmount);
        }
        
        // Create the API request and set the parameters for this specific request
        CreateTransactionRequest apiRequest = new CreateTransactionRequest();
        apiRequest.setMerchantAuthentication(merchantAuthenticationType);
        apiRequest.setTransactionRequest(txnRequest);

        // Call the controller
        CreateTransactionController controller = new CreateTransactionController(apiRequest);
        controller.execute();

        // Get the response
        CreateTransactionResponse response = new CreateTransactionResponse();
        response = controller.getApiResponse();
//        
        

        // Parse the response to determine results
        if (response!=null) {
            // If API Response is OK, go ahead and check the transaction response
            if (response.getMessages().getResultCode() == MessageTypeEnum.OK) {
                TransactionResponse result = response.getTransactionResponse();                
                if (result.getMessages() != null) {
                    System.out.println("Successfully created transaction with Transaction ID: " + result.getTransId());
                    System.out.println("Response Code: " + result.getResponseCode());
                    System.out.println("Message Code: " + result.getMessages().getMessage().get(0).getCode());
                    System.out.println("Description: " + result.getMessages().getMessage().get(0).getDescription());
                    System.out.println("Auth Code: " + result.getAuthCode());             
                    
                    System.out.println("Name : "+customeraddress.getFirstName());
                    System.out.println("Address : "+customeraddress.getAddress());
                    System.out.println("City : "+customeraddress.getCity());
                    System.out.println("Country : "+customeraddress.getCountry());
                    System.out.println("zip : "+customeraddress.getZip());
                } else {
                    System.out.println("Failed Transaction.");
                    if (response.getTransactionResponse().getErrors() != null) {
                        System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                        System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                    }
                }
            } else {
                System.out.println("Failed Transaction.");
                if (response.getTransactionResponse() != null && response.getTransactionResponse().getErrors() != null) {
                    System.out.println("Error Code: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorCode());
                    System.out.println("Error message: " + response.getTransactionResponse().getErrors().getError().get(0).getErrorText());
                } else {
                    System.out.println("Error Code: " + response.getMessages().getMessage().get(0).getCode());
                    System.out.println("Error message: " + response.getMessages().getMessage().get(0).getText());
                }
            }
        } else {
            // Display the error code and message when response is null 
            ANetApiResponse errorResponse = controller.getErrorResponse();
            System.out.println("Failed to get response");
            if (!errorResponse.getMessages().getMessage().isEmpty()) {
                System.out.println("Error: "+errorResponse.getMessages().getMessage().get(0).getCode()+" \n"+ errorResponse.getMessages().getMessage().get(0).getText());
            }
        }
        
        return response;
    }
}
