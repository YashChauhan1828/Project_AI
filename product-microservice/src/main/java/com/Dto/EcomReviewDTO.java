package com.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomReviewDTO 
{
	Integer reviewId;
	Integer rating;
	String comment;
	LocalDateTime createdAt;
	UUID userId;
	String first_name;
	Integer productId;
}
