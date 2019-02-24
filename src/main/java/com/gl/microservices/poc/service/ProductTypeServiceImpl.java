package com.gl.microservices.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.microservices.poc.entity.ProductType;
import com.gl.microservices.poc.repository.ProductTypeRepository;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	ProductTypeRepository productTypeRepository;

	@Override
	public void saveOrUpdate(ProductType productType) {
		productTypeRepository.save(productType);

	}

	@Override
	public ProductType findProductTypeById(String productTypeId) {
		return productTypeRepository.findOne(productTypeId);
	}

	@Override
	public List<ProductType> findAllProductType() {
		return productTypeRepository.findAll();
	}

	@Override
	public void deleteProductTypeById(String productTypeId) {
		productTypeRepository.delete(productTypeId);

	}

	@Override
	public ProductType findProductTypeByName(String name) {
		return productTypeRepository.findByName(name);
	}

}
