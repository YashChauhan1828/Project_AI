package com.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entity.EcomCartEntity;


public interface CartRepository extends JpaRepository<EcomCartEntity, Integer> 
{
	EcomCartEntity findByUserId(UUID userId);
}
