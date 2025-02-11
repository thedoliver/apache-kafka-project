package com.denis.strconsumer.listeners;

import com.denis.strconsumer.custom.StrConsumerCustomListener;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class StrConsumerListerner {

    private static final Logger log = LogManager.getLogger(StrConsumerListerner.class);

    @SneakyThrows
    @StrConsumerCustomListener(groupId = "group-1")
    public void create(String message) {
        log.info("CREATE ::: Received message: {}", message);
        throw new IllegalArgumentException("EXCEPTION ...");
    }

    @StrConsumerCustomListener(groupId = "group-1")
    public void log(String message) {
        log.info("LOG ::: Received message: {}", message);
    }

    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "validMessageContainerFactory")
    public void history(String message) {
        log.info("HISTORY ::: Received message: {}", message);
    }
}
