package com.gl.microservices.poc.service;

import java.util.List;

import com.gl.microservices.poc.entity.Brand;

public interface BrandService {

	void createOrUpdate(Brand brand);
	Brand findBrandById(String brandId);
	List<Brand> findAllBrand();
	void deleteBrandById(String brandId);
	Brand findBrandByName(String name);
}
