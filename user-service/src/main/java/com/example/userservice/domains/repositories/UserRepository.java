package com.example.userservice.domains.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.userservice.domains.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	@Query(value = "select u.email from UserEntity u")
	List<String> findAllEmails();

	Optional<UserEntity> findUserEntityById(Long id);
}
