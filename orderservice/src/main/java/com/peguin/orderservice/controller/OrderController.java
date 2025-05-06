package com.peguin.orderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peguin.orderservice.model.OrderRequest;
import com.peguin.orderservice.service.OrderService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("${api.prefix}/order")
@Log4j2
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> createOrder(@RequestBody OrderRequest orderRequest) {
        long orderId = orderService.createOrder(orderRequest);
        log.info("Order created with ID: {}", orderId);
        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }
}
