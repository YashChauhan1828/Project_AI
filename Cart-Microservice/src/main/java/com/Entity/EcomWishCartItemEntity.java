package com.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "wishcartitem")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomWishCartItemEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer wishcartitemId;

	@ManyToOne
	@JoinColumn(name = "wishcart_id")
	@JsonIgnore
	EcomWishList wishcart;
	
	String productName;
	 String category;
	 float price;
	 String productImagePath;
}
