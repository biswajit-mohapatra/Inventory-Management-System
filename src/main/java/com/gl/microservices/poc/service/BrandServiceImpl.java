package com.gl.microservices.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.microservices.poc.entity.Brand;
import com.gl.microservices.poc.repository.BrandRepository;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	BrandRepository brandRepository;

	@Override
	public void createOrUpdate(Brand brand) {
		brandRepository.save(brand);
	}

	@Override
	public Brand findBrandById(String brandId) {
		return brandRepository.findOne(brandId);
	}

	@Override
	public List<Brand> findAllBrand() {
		return brandRepository.findAll();
	}

	@Override
	public void deleteBrandById(String brandId) {
		brandRepository.delete(brandId);
	}

	@Override
	public Brand findBrandByName(String name) {
		// TODO Auto-generated method stub
		return brandRepository.findByName(name);
	}
}
