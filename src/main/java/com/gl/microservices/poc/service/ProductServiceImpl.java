package com.gl.microservices.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.microservices.poc.entity.Product;
import com.gl.microservices.poc.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public void saveOrUpdate(Product product) {
		productRepository.save(product);

	}

	@Override
	public Product findProductById(String productId) {
		return productRepository.findOne(productId);
	}

	@Override
	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public void deleteProductById(String productId) {
		productRepository.delete(productId);

	}

	@Override
	public Product findProductByName(String productName) {
		return productRepository.findByName(productName);
	}

}
