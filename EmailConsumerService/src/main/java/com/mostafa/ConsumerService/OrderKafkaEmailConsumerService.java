package com.mostafa.ConsumerService;

import com.mostafa.baseservice.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OrderKafkaEmailConsumerService {
    private Logger logger = LoggerFactory.getLogger(OrderKafkaEmailConsumerService.class);

    private JavaMailSender javaMailSender;

    public OrderKafkaEmailConsumerService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Consume specified group's and topic's data and send the data using email
     */
    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeString(Order order){
        logger.info("Consumed message: "+order.toString());

        this.sendEmail("abcd@outlook.com", order.getDescription()+" order completed!", "Order Details : "+order.toString());
    }

    /**
     * send email from specified email to another specified email with subject and email body
     * @param email
     * @param subject
     * @param messageBody
     */
    public void sendEmail(String email, String subject, String messageBody){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("abcd@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(messageBody);

        this.javaMailSender.send(message);
    }
}
