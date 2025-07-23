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
	 Integer cartitemId;
     Integer productId;
     String productName;
     Float price;
     String category;
     Integer qty;
     String productImagePath;
}
