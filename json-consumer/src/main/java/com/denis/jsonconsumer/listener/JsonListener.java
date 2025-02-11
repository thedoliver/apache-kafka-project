package com.denis.jsonconsumer.listener;

import com.denis.jsonconsumer.model.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

@Component
public class JsonListener {

    private static final Logger log = LogManager.getLogger(JsonListener.class);

    @KafkaListener(topics = "payment-topic", groupId = "create-group", containerFactory = "jsonContainerFactory")
    public void antiFraud(@Payload Payment payment) throws InterruptedException {
        log.info("Recebi o pagamento {}", payment);
        log.info("Validando fraude ...");
        sleep(2000);
        log.info("Compra aprovada ...");
        sleep(3000);
    }

    @KafkaListener(topics = "payment-topic", groupId = "pdf-group", containerFactory = "jsonContainerFactory")
    public void pdfGenerator(@Payload Payment payment) throws InterruptedException {
        log.info("Gerando PDF para pagamento {}", payment);
        sleep(2000);
    }

    @KafkaListener(topics = "payment-topic", groupId = "email-group", containerFactory = "jsonContainerFactory")
    public void sendEmail(@Payload Payment payment) {
        log.info("Enviando email de confirmação para pagamento {}", payment);
    }
}
