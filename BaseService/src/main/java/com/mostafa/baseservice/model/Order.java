package com.mostafa.baseservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private String description;
    private int quantity;
    private double price;
    private String message;
    private String status;
}
