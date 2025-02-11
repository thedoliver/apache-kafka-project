package com.denis.jsonconsumer.config;

import com.denis.jsonconsumer.model.Payment;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;

@Configuration
public class JsonConsumerConfig {

    private final KafkaProperties properties;

    public JsonConsumerConfig(KafkaProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ConsumerFactory<String, Payment> jsonConsumerFactory() {
        var configs = new HashMap<String, Object>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configs.put(JsonDeserializer.TRUSTED_PACKAGES, "*");  // Allow deserialization of all packages

        return new DefaultKafkaConsumerFactory<>(
                configs,
                new StringDeserializer(),
                new JsonDeserializer<>(Payment.class) // Deserialize directly to Payment object
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Payment> jsonContainerFactory(
            ConsumerFactory<String, Payment> jsonConsumerFactory
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, Payment>();
        factory.setConsumerFactory(jsonConsumerFactory);
        return factory;
    }
}
