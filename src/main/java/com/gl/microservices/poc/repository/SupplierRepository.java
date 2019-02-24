package com.gl.microservices.poc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gl.microservices.poc.entity.Brand;
import com.gl.microservices.poc.entity.Supplier;

public interface SupplierRepository extends MongoRepository<Supplier, String> {
	public Supplier findByName(String name);

}
