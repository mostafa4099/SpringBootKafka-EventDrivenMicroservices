package com.mostafa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="order_data")
public class OrderData {
    @Id
    private String id;
    private String description;
    private int quantity;
    private double price;
    private String message;
    private String status;
}
