package com.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "ecomshippingdetails")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomShippingEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer shippingId;
	String recipientName;
	String address;
	String city;
	String state;
	String zipCode;
	String country;
	String phoneNumber;
	@ManyToOne
	@JoinColumn(name = "user_id")
	UserEntity user;
}
