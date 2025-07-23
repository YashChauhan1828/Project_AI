package com.DTO;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomCartItemDto 
{
	UUID userId;
	Integer productId;
	Integer cartitemId; 
	String productName;
	 String category;
	 Integer qty;
	 float price;
	 String productImagePath;
	
}
