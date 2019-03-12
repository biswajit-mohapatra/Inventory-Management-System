package com.gl.microservices.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.gl.microservices.poc.entity.Supplier;
import com.gl.microservices.poc.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	SupplierRepository supplierRepository;

	@Override
	public void create(Supplier supplier) {
		supplierRepository.save(supplier);
		
	}

	@Override
	@Cacheable(value="supplierCache",key="#supplierId")
	public Supplier findSupplierById(String supplierId) {
		return supplierRepository.findOne(supplierId);
	}

	@Override
	@Cacheable(value="supplierCache")
	public List<Supplier> findAllSupplier() {
		return supplierRepository.findAll();
	}

	@Override
	@CacheEvict(value="supplierCache",key="#supplierId")
	public void deleteSupplierById(String supplierId) {
		supplierRepository.delete(supplierId);
		
	}

	@Override
	@Cacheable(value="supplierCache",key="#name")
	public Supplier findSupplierByName(String name) {
		return supplierRepository.findByName(name);
	}

}
