package com.example.userservice.domains.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userservice.domains.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
