package com.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomProductDTO 
{
	Integer productId;
	 String productName;
	 String category;
	 Integer qty;
	 float price;
	 String productImagePath;
}
