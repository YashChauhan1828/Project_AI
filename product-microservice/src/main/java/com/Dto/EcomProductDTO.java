package com.Dto;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomProductDTO implements Serializable 
{
	 private static final long serialVersionUID = 1L;
	Integer productId;
	 String productName;
	 String category;
	 Integer qty;
	 float price;
	 String productImagePath;
}
