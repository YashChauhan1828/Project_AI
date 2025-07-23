package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.EcomProductEntity;

import jakarta.transaction.Transactional;

@Repository
public interface EcomProductRepository extends JpaRepository<EcomProductEntity, Integer>
{
	List<EcomProductEntity> findByCategoryContainingIgnoreCaseOrProductNameContainingIgnoreCase(String category,
			String productName);
	
//	List<EcomProductEntity> findByProductNameContainingIgnoreCaseAndPriceLessThan(String productName, float price);
	@Modifying
	@Transactional
	@Query(value = "UPDATE ecomproducts SET qty = qty - ? WHERE product_id = ? AND qty >= ?",nativeQuery = true)
	int reduceProductQuantity(Integer qty,Integer productId,Integer minimum );
	
	@Query(value = "SELECT * FROM ecomproducts LIMIT 9", nativeQuery = true)
	List<EcomProductEntity> findTop9();
}
