package com.Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "orderitementity")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE )
public class EcomOrderItemEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer orderItemId;
	String Status;
	LocalDateTime orderDate;
	Integer qty;
	
	@ManyToOne
	@JoinColumn(name = "orderId")
	@JsonIgnore
	EcomOrderEntity order;

	String productName;
	 String category;
	 float price;
	 String productImagePath;
}
