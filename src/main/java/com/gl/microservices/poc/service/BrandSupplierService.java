package com.gl.microservices.poc.service;

import java.util.List;

import com.gl.microservices.poc.entity.BrandSupplier;

public interface BrandSupplierService {

	void createOrUpdate(BrandSupplier brandSupplier);

	BrandSupplier findBrandSupplierById(String brandSupplierId);

	List<BrandSupplier> findAllBrandSuuplier();

	void deleteBrandSupplierById(String brandSupplierId);

	List<BrandSupplier> findBrandSuppliersByBrandName(String brandName);
	
	List<BrandSupplier> findBrandSuppliersBySupplierName(String supplierName);
	
	BrandSupplier findBrandSupplierForBrandAndSupplier(String brandId,String supplier);
	
	
}
