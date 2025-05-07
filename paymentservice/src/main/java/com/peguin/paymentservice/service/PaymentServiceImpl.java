package com.peguin.paymentservice.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.peguin.paymentservice.entity.TransactionDetails;
import com.peguin.paymentservice.model.PaymentRequest;
import com.peguin.paymentservice.repository.TransactionDetailsRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    private TransactionDetailsRepository transactionDetailsRepository;

    private PaymentServiceImpl(TransactionDetailsRepository transactionDetailsRepository) {
        this.transactionDetailsRepository = transactionDetailsRepository;
    }

    @Override
    public long makePayment(PaymentRequest paymentRequest) {
        log.info("Payment request received: {}", paymentRequest);

        TransactionDetails details = TransactionDetails.builder()
                .orderId(paymentRequest.getOrderId())
                .amount(paymentRequest.getAmount())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .paymentMethod(paymentRequest.getPaymentMode().name())
                .paymentDate(Instant.now())
                .paymentStatus("SUCCESS")
                .build();

        transactionDetailsRepository.save(details);

        log.info("Payment details saved: {}", details);

        return details.getId();
    }
}
