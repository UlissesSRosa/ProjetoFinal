package com.example.userservice.domains.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.userservice.domains.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	@Query(value = "select u.email from UserEntity u")
	List<String> findAllEmails();
}
