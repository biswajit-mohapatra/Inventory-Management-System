package com.gl.microservices.poc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gl.microservices.poc.entity.BrandSupplier;

public interface BrandSupplierRepository extends MongoRepository<BrandSupplier, String> {
	public List<BrandSupplier> findByBrand(String brandId);
	public List<BrandSupplier> findBySupplier(String supplierId);
	public BrandSupplier findByBrandAndSupplier(String brandId,String supplierId);

}
