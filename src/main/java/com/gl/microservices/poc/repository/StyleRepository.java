package com.gl.microservices.poc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gl.microservices.poc.entity.Style;

public interface StyleRepository extends MongoRepository<Style, String> {
	public Style findByStyleNumber(String styleNumber);

}
