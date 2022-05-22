package com.example.managementservice.domains.clients;

import com.example.managementservice.domains.dtos.AdressDTO;
import com.example.managementservice.domains.dtos.RoleDTO;
import com.example.managementservice.domains.entities.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "USER-SERVICE", url = "http://localhost:8081/")

public interface UserClient {

    @GetMapping(value = "v1/users/entity/{userId}")
    UserEntity getUser(@PathVariable Long userId);

    @GetMapping(value = "v1/permissions/{permissionId}")
    RoleDTO getRole(@PathVariable Long permissionId);

    @GetMapping(value = "v1/adress/{userId}")
    AdressDTO getAllAdress(@PathVariable Long userId);
}
