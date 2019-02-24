package com.gl.microservices.poc.service;

import java.util.List;

import com.gl.microservices.poc.entity.Supplier;

public interface SupplierService {

	void create(Supplier supplier);
	Supplier findSupplierById(String supplierId);
	List<Supplier> findAllSupplier();
	void deleteSupplierById(String supplierId);
	Supplier findSupplierByName(String name);
}
