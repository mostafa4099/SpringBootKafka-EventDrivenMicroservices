package com.mostafa.repository;

import com.mostafa.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDataRepository extends JpaRepository<OrderData, String> {
}
