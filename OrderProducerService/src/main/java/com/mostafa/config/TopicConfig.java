package com.mostafa.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
    /**
     * retrieve topic name from application.properties file.
     */
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    /**
     * Create new topic with default partition if not exist.
     * @return new created topic
     */
    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(topicName)
//                .partitions(3)
                .build();
    }
}
