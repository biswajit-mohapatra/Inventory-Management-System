package com.gl.microservices.poc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gl.microservices.poc.entity.Brand;

public interface BrandRepository extends MongoRepository<Brand, String> {
	public Brand findByName(String name);

}
