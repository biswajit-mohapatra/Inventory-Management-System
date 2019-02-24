package com.gl.microservices.poc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gl.microservices.poc.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	public Product findByName(String name);

}
