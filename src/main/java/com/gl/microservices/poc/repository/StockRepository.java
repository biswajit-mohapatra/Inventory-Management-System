package com.gl.microservices.poc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gl.microservices.poc.entity.Brand;
import com.gl.microservices.poc.entity.Stock;

public interface StockRepository extends MongoRepository<Stock, String> {

}
