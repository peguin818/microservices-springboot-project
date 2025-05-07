package com.peguin.paymentservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peguin.paymentservice.model.PaymentRequest;
import com.peguin.paymentservice.service.PaymentService;

@RestController
@RequestMapping("${api.prefix}/payment")
public class PaymentController {
    private PaymentService paymentService;

    private PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public ResponseEntity<Long> makePayment(@RequestBody PaymentRequest paymentRequest) {
        return new ResponseEntity<>(paymentService.makePayment(paymentRequest), HttpStatus.OK);
    }
}
