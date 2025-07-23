package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.EcomProductEntity;
import com.entity.EcomReviewEntity;

public interface EcomReviewRepository extends JpaRepository<EcomReviewEntity, Integer> 
{
	List<EcomReviewEntity> findByProduct(EcomProductEntity product);
}
