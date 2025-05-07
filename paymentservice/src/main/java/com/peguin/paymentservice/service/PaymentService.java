package com.peguin.paymentservice.service;

import com.peguin.paymentservice.model.PaymentRequest;

public interface PaymentService {

    long makePayment(PaymentRequest paymentRequest);

}
