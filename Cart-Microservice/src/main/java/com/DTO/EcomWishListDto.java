package com.DTO;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomWishListDto 
{
	UUID userId;
	Integer productId;
	Integer wishcartitemId;
	String productName;
	String category;
	float price;
	String productImagePath;
}
