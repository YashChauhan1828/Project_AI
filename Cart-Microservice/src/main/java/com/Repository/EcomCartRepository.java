package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entity.EcomCartEntity;
import com.Entity.EcomCartItemEntity;



public interface EcomCartRepository extends JpaRepository<EcomCartItemEntity, Integer>
{
	List<EcomCartItemEntity> findByCart(EcomCartEntity cart);
	EcomCartItemEntity findByCartitemId(Integer cartitemId);
//	List<EcomCartItemEntity> findByCartitemId(Integer cartitemId);
	EcomCartItemEntity save(Integer qty);
}
