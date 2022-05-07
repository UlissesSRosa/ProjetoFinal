package com.example.managementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"com.example.core.model"})
@EnableJpaRepositories({"com.example.core.repositories"})
@EnableEurekaClient
public class ManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementServiceApplication.class, args);
    }

}
