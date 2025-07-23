package com.Dto;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomPaymentDto 
{
	UUID userId;
	String creditcardnumber;
	String date;
	String cvv;
	Float price;
	String email;
	String recipientName;
	String address;
	String city;
	String state;
	String zipCode;
	String country;
	
}
