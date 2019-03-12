package com.gl.microservices.poc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
  @Cacheable(value = "productCache", key = "#productId")
  public Product findProductById(String productId) {
    return productRepository.findOne(productId);
  }

  @Override
  @Cacheable(value = "productCache")
  public List<Product> findAllProduct() {
    return productRepository.findAll();
  }

  @Override
  @CacheEvict(value = "productCache", key = "#productId")
  public void deleteProductById(String productId) {
    productRepository.delete(productId);

  }

  @Override
  @Cacheable(value = "productCache", key = "#productName")
  public Product findProductByName(String productName) {
    return productRepository.findByName(productName);
  }

}
