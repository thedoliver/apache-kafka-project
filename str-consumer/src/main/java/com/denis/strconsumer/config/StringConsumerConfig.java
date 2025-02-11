package com.denis.strconsumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.RecordInterceptor;

import java.util.HashMap;

@Configuration
public class StringConsumerConfig {

    private static final Logger log = LogManager.getLogger(StringConsumerConfig.class);

    @Autowired
    private KafkaProperties properties;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        var configs = new HashMap<String, Object>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configs);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> strContainerFactory(
            ConsumerFactory<String, String> consumerFactory // Use the parameter here
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory); // Use the passed consumerFactory
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> validMessageContainerFactory(
            ConsumerFactory<String, String> consumerFactory // Use the parameter here
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory); // Use the passed consumerFactory
        factory.setRecordInterceptor(validMessage());
        return factory;
    }
// Interceptor
    private RecordInterceptor<String, String> validMessage() {
        return record -> {
            if (record.value().contains("Teste")) {
                log.info("Possui a palavra teste");
                return record;
            }
            return record;
        };
    }
}