package com.example.userservice.domains.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "register", nullable = false)
    private LocalDateTime register;

    @Column(name = "updatedRegister", nullable = false)
    private LocalDateTime updatedRegister;

    public BaseEntityModel() {
    }

    @PrePersist
    protected void onCreate() {
        this.updatedRegister = this.register = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedRegister = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getRegister() {
        return register;
    }

    public void setRegister(LocalDateTime register) {
        this.register = register;
    }

    public LocalDateTime getUpdatedRegister() {
        return updatedRegister;
    }

    public void setUpdatedRegister(LocalDateTime updatedRegister) {
        this.updatedRegister = updatedRegister;
    }
}
