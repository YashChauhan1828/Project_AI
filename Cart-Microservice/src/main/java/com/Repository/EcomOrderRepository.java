package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entity.EcomOrderEntity;
import java.util.UUID;

public interface EcomOrderRepository extends JpaRepository<EcomOrderEntity, Integer> 
{
	List<EcomOrderEntity> findByUserId(UUID userId); 
}
