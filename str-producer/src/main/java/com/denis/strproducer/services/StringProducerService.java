package com.denis.strproducer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class StringProducerService {

    private static final Logger log = LogManager.getLogger(StringProducerService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send("str-topic", message).addCallback(
                success -> {
                    if (success != null) {
                        log.info("Message [{}] sent successfully to topic [{}] at partition [{}] with offset [{}]",
                                message,
                                success.getRecordMetadata().topic(),
                                success.getRecordMetadata().partition(),
                                success.getRecordMetadata().offset());
                    }
                },
                error -> log.error("Error sending message [{}]: {}", message, error.getMessage(), error)
        );
    }
}
