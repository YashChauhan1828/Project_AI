package com.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entity.EcomOrderEntity;
import com.Entity.EcomOrderItemEntity;


public interface EcomOrderItemRepository extends JpaRepository<EcomOrderItemEntity, Integer>
{
	List<EcomOrderItemEntity> findByOrder(EcomOrderEntity order);
//	EcomOrderItemEntity findByOrderItemId(Integer orderitemId);

	 
}
