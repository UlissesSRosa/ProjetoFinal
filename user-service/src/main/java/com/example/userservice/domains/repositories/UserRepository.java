package com.example.userservice.domains.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userservice.domains.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
