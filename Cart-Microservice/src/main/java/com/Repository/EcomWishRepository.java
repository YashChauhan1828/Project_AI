package com.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entity.EcomWishList;


public interface EcomWishRepository extends JpaRepository<EcomWishList, Integer>  
{
	EcomWishList findByUserId(UUID userId);
}
