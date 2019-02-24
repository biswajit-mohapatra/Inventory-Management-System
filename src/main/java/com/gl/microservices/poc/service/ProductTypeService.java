package com.gl.microservices.poc.service;

import java.util.List;

import com.gl.microservices.poc.entity.Brand;
import com.gl.microservices.poc.entity.ProductType;

public interface ProductTypeService {

	void saveOrUpdate(ProductType productType);
	ProductType findProductTypeById(String productTypeId);
	List<ProductType> findAllProductType();
	void deleteProductTypeById(String productTypeId);
	ProductType findProductTypeByName(String name);
}
