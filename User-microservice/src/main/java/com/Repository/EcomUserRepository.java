package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Entity.UserEntity;
import java.util.Optional;
import java.util.UUID;


public interface EcomUserRepository extends JpaRepository<UserEntity, UUID>
{
	UserEntity findByEmailAndPassword(String email, String password); 
	UserEntity findByEmail(String email);
	Optional<UserEntity>  findByToken(String token);
	UserEntity save(Optional<UserEntity> role);
}
