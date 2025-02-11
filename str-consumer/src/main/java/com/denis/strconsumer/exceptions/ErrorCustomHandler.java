package com.denis.strconsumer.exceptions;

import com.denis.strconsumer.listeners.StrConsumerListerner;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ErrorCustomHandler implements KafkaListenerErrorHandler {

    private static final Logger log = LogManager.getLogger(ErrorCustomHandler.class);

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException e) {
        log.info("EXCEPTION_HANDLER ::: Capturei um erro");
        log.info("Payload ::: {}", message.getPayload());
        log.info("Headers ::: {}", message.getHeaders());
        log.info("OffSet ::: {}", message.getHeaders().get("kafka_offset"));
        log.info("Message exception ::: {}", e.getMessage());
        return null;
    }

//    @Override
//    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
//        return KafkaListenerErrorHandler.super.handleError(message, exception, consumer);
//    }
}
