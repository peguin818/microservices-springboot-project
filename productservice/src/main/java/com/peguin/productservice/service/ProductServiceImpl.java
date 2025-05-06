package com.peguin.productservice.service;

import org.springframework.stereotype.Service;

import com.peguin.productservice.entity.Product;
import com.peguin.productservice.exception.ProductServiceCustomException;
import com.peguin.productservice.model.ProductRequest;
import com.peguin.productservice.model.ProductResponse;
import com.peguin.productservice.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    // Constructor-based dependency injection is preferred for better testability
    // and immutability
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public long createProduct(ProductRequest productDTO) {
        log.info("Creating product with name: {}", productDTO.getName());

        Product product = Product.builder()
                .productName(productDTO.getName())
                .price(productDTO.getPrice())
                .quantity(productDTO.getQuantity())
                .build();

        Product savedProduct = productRepository.save(product);

        log.info("Product created with ID: {}", savedProduct.getId());
        return savedProduct.getId();
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        log.info("Getting product by ID: {}", productId);

        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("Product not found with ID: " + productId, "404"));
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

        log.info("Product found: {}", productResponse);
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("reducing quantity for product ID: {} by {}", productId, quantity);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("Product not found with ID: " + productId, "404"));

        if (quantity <= 0) {
            throw new ProductServiceCustomException("Quantity must be greater than zero", "400");
        }

        if (product.getQuantity() < quantity) {
            throw new ProductServiceCustomException("Insufficient quantity for product ID: " + productId, "400");
        }

        product.setQuantity(product.getQuantity() - quantity);

        Product updatedProduct = productRepository.save(product);
        log.info("Product quantity updated: {}", updatedProduct);
    }
}
