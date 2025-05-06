package com.peguin.orderservice.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.peguin.orderservice.entity.Order;
import com.peguin.orderservice.external.client.ProductService;
import com.peguin.orderservice.model.OrderRequest;
import com.peguin.orderservice.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductService productService;

    private OrderServiceImpl(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    @Override
    public long createOrder(OrderRequest orderRequest) {
        log.info("Creating order with request: {}", orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        log.info("Product quantity reduced for product ID: {}", orderRequest.getProductId());

        Order order = Order.builder()
                .amount(orderRequest.getAmount())
                .status("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        Order savedOrder = orderRepository.save(order);
        log.info("Order created with ID: {}", savedOrder.getId());

        return savedOrder.getId();
    }

}
