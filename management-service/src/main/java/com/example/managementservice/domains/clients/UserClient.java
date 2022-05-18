package com.example.managementservice.domains.clients;

<<<<<<< HEAD
import com.example.managementservice.domains.dtos.AdressDTO;
import com.example.managementservice.domains.dtos.RoleDTO;
import com.example.managementservice.domains.dtos.UserDTO;
import com.example.managementservice.domains.entities.UserEntity;
=======
>>>>>>> 76d06f804e8636a60fa7c1f8ecaa3b95479d2348
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

<<<<<<< HEAD
@FeignClient(value = "USER-SERVICE", url = "http://localhost:8081/")
=======
import com.example.managementservice.domains.dtos.AdressDTO;
import com.example.managementservice.domains.dtos.RoleDTO;
import com.example.managementservice.domains.dtos.UserDTO;

@FeignClient(value = "USER-SERVICE")
>>>>>>> 76d06f804e8636a60fa7c1f8ecaa3b95479d2348
public interface UserClient {

    @GetMapping(value = "v1/users/entity/{userId}")
    UserEntity getUser(@PathVariable Long userId);

    @GetMapping(value = "v1/permissions/{permissionId}")
    RoleDTO getRole(@PathVariable Long permissionId);

    @GetMapping(value = "v1/adress/{userId}")
    AdressDTO getAllAdress(@PathVariable Long userId);
}
