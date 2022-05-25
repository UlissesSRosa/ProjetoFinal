package com.example.orderservice.domains.enums;

import lombok.ToString;

@ToString
public enum OrderStatus {

    OPEN("OPEN"),
    FINISHED("FINISHED");

    public final String value;

    private OrderStatus(String value) {
        this.value = value;
    }
}
