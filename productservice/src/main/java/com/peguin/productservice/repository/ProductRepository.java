package com.peguin.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peguin.productservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
