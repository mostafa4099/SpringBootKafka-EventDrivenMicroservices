package com.mostafa.KafkaService;

import com.mostafa.baseservice.model.Order;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    private KafkaTemplate<String, Order> kafkaTemplate;
    private NewTopic topic;

    public KafkaProducerService(KafkaTemplate<String, Order> kafkaTemplate, NewTopic topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    /**
     * Publish order event message in kafka cluster within specified topic
     */
    public void sendMessage(Order event) {
        logger.info("Order Event: "+event.toString());

        Message<Order> eventMessage = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
        kafkaTemplate.send(eventMessage);
    }
}
