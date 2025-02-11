package com.denis.paymentservice.service.impl;

import com.denis.paymentservice.model.Payment;
import com.denis.paymentservice.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger log = LogManager.getLogger(PaymentServiceImpl.class);

    @Override
    public void sendPayment(Payment payment) {
        log.info("PAYMENT_SEVICE_IMPL ::: Recebi o pagamento {}", payment);
    }
}
