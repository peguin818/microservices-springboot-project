package com.peguin.productservice.service;

import com.peguin.productservice.model.ProductRequest;
import com.peguin.productservice.model.ProductResponse;

public interface ProductService {

    long createProduct(ProductRequest productDTO);

    ProductResponse getProductById(Long productId);

    void reduceQuantity(long productId, long quantity);

}
