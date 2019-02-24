package com.gl.microservices.poc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.gl.microservices.poc.entity.ProductType;

public interface ProductTypeRepository extends MongoRepository<ProductType, String> {
	public ProductType findByName(String name);

}
