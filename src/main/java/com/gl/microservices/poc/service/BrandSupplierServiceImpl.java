package com.gl.microservices.poc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.microservices.poc.entity.Brand;
import com.gl.microservices.poc.entity.BrandSupplier;
import com.gl.microservices.poc.entity.Supplier;
import com.gl.microservices.poc.repository.BrandRepository;
import com.gl.microservices.poc.repository.BrandSupplierRepository;
import com.gl.microservices.poc.repository.SupplierRepository;

@Service
public class BrandSupplierServiceImpl implements BrandSupplierService {

	@Autowired
	BrandSupplierRepository brandSupplierRepository;

	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	SupplierRepository supplierRepository;
	

	@Override
	public void createOrUpdate(BrandSupplier brandSupplier) {
		brandSupplierRepository.save(brandSupplier);

	}

	@Override
	public BrandSupplier findBrandSupplierById(String brandSupplierId) {
		// TODO Auto-generated method stub
		return brandSupplierRepository.findOne(brandSupplierId);
	}

	@Override
	public List<BrandSupplier> findAllBrandSuuplier() {
		return brandSupplierRepository.findAll();
	}

	@Override
	public void deleteBrandSupplierById(String brandSupplierId) {
		brandSupplierRepository.delete(brandSupplierId);

	}

	@Override
	public List<BrandSupplier> findBrandSuppliersByBrandName(String brandName) {
		Brand brand = brandRepository.findByName(brandName);
		List<BrandSupplier> brandSuppliers = new ArrayList<BrandSupplier>();
		if (brand != null)
			brandSuppliers.addAll(brandSupplierRepository.findByBrand(brand.getId()));
		return brandSuppliers;
	}

	@Override
	public List<BrandSupplier> findBrandSuppliersBySupplierName(String supplierName) {
		Supplier supplier = supplierRepository.findByName(supplierName);
		List<BrandSupplier> brandSuppliers = new ArrayList<BrandSupplier>();
		if (supplier != null)
			brandSuppliers.addAll(brandSupplierRepository.findBySupplier(supplier.getId()));
		return brandSuppliers;
	}

	@Override
	public BrandSupplier findBrandSupplierForBrandAndSupplier(String brandId, String supplierId) {
		// TODO Auto-generated method stub
		return brandSupplierRepository.findByBrandAndSupplier(brandId, supplierId);
	}
}
