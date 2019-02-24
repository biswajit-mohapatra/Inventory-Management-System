package com.gl.microservices.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Supplier findSupplierById(String supplierId) {
		return supplierRepository.findOne(supplierId);
	}

	@Override
	public List<Supplier> findAllSupplier() {
		return supplierRepository.findAll();
	}

	@Override
	public void deleteSupplierById(String supplierId) {
		supplierRepository.delete(supplierId);
		
	}

	@Override
	public Supplier findSupplierByName(String name) {
		return supplierRepository.findByName(name);
	}

}
