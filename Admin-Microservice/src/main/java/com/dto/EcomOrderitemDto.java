package com.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomOrderitemDto 
{

	UUID userId;
	Integer productId;
	Integer orderItemId;
	String Status;
	LocalDateTime orderDate;
	String productName;
	 String category;
	 Integer qty;
	 float price;
	 String productImagePath;
}
