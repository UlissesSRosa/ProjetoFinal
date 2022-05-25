package com.example.orderservice.domains.clients;

import com.example.orderservice.domains.entities.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "USER-SERVICE", url = "http://localhost:8081/")
public interface UserClient {

    @GetMapping(value ="v1/users/entity/{userId}")
    UserEntity getUser(@PathVariable Long userId);
    
}
