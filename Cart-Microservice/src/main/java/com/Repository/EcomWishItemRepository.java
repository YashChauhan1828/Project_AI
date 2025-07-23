package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entity.EcomWishCartItemEntity;
import com.Entity.EcomWishList;

import java.util.List;


public interface EcomWishItemRepository extends JpaRepository<EcomWishCartItemEntity, Integer> 
{
	List<EcomWishCartItemEntity> findByWishcart(EcomWishList wishcart);
//	EcomWishCartItemEntity  findByWishcartitemId(Integer wishcartitemId);
}
