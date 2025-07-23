package com.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Entity.EcomShippingEntity;

public interface EcomShippingRepository extends JpaRepository<EcomShippingEntity, Integer>  
{
	@Query(value = "SELECT * FROM ecomshippingdetails WHERE user_id = ? ORDER BY shipping_id DESC LIMIT 1", nativeQuery = true)
	EcomShippingEntity findLatestShippingForUser(UUID userId);
}
