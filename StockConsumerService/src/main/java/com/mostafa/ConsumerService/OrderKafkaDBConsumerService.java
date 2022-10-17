package com.mostafa.ConsumerService;

import com.mostafa.baseservice.model.Order;
import com.mostafa.entity.OrderData;
import com.mostafa.repository.OrderDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderKafkaDBConsumerService {
    private Logger logger = LoggerFactory.getLogger(OrderKafkaDBConsumerService.class);

    OrderDataRepository repository;

    public OrderKafkaDBConsumerService(OrderDataRepository repository) {
        this.repository = repository;
    }

    /**
     * Consume specified group's and topic's data and save the data in db
     */
    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeString(Order order){
        logger.info("Consumed message: "+order.toString());

        OrderData data = new OrderData(
                order.getOrderId(),
                order.getDescription(),
                order.getQuantity(),
                order.getPrice(),
                order.getMessage(),
                order.getStatus());

        repository.save(data);
    }
}
