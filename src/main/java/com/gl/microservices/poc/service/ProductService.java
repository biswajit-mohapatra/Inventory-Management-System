package com.gl.microservices.poc.service;

import java.util.List;

import com.gl.microservices.poc.entity.Product;

public interface ProductService {

	void saveOrUpdate(Product product);

	Product findProductById(String productId);

	List<Product> findAllProduct();

	void deleteProductById(String productId);

	Product findProductByName(String productName);

}
