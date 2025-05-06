package com.peguin.orderservice.service;

import com.peguin.orderservice.model.OrderRequest;

public interface OrderService {

    long createOrder(OrderRequest orderRequest);

}
