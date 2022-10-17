package com.mostafa.controller;

import com.mostafa.KafkaService.KafkaProducerService;
import com.mostafa.baseservice.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    private KafkaProducerService producerService;

    public OrderController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    /**
     * Post order to kafka producer service to produce new order message.
     * @param order
     * @return
     */
    @PostMapping
    public String order(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        order.setStatus("Pending");
        order.setMessage("Order place successfully!");

        producerService.sendMessage(order);

        return "Order place successfully";
    }
}
