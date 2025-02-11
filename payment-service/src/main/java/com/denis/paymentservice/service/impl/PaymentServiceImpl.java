package com.denis.paymentservice.service.impl;

import com.denis.paymentservice.model.Payment;
import com.denis.paymentservice.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger log = LogManager.getLogger(PaymentServiceImpl.class);
    private final KafkaTemplate<String, Payment> kafkaTemplate;

    public PaymentServiceImpl(KafkaTemplate<String, Payment> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendPayment(Payment payment) {
        log.info("Processing payment: {}", payment);

        kafkaTemplate.send("payment-topic", payment);
    }
}
