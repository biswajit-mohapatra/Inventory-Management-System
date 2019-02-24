package com.gl.microservices.poc.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.microservices.poc.entity.BrandSupplier;
import com.gl.microservices.poc.service.BrandService;
import com.gl.microservices.poc.service.BrandSupplierService;
import com.gl.microservices.poc.service.SupplierService;
import com.gl.microservices.poc.util.URI;

@RestController
@RequestMapping(value = URI.BRAND_SUP)
public class BrandSupplierController {

	@Autowired
	BrandSupplierService brandSupplierService;

	@Autowired
	BrandService brandService;

	@Autowired
	SupplierService supplierService;

	@PostMapping(value = URI.CREATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody BrandSupplier brandSupplier) {

		if (!StringUtils.hasText(brandSupplier.getBrand().getId()))
			return new ResponseEntity<>("Brand ID can't be Blank. Bad request.", HttpStatus.BAD_REQUEST);

		if (!StringUtils.hasText(brandSupplier.getSupplier().getId()))
			return new ResponseEntity<>("Supplier ID can't be Blank. Bad request.", HttpStatus.BAD_REQUEST);

		if (brandService.findBrandById(brandSupplier.getBrand().getId()) == null)
			return new ResponseEntity<>("Brand has not in the system. Bad request.", HttpStatus.BAD_REQUEST);

		if (supplierService.findSupplierById(brandSupplier.getSupplier().getId()) == null)
			return new ResponseEntity<>("Supplier has not in the system. Bad request.", HttpStatus.BAD_REQUEST);

		BrandSupplier existBrandSupplier = brandSupplierService.findBrandSupplierForBrandAndSupplier(
				brandSupplier.getBrand().getId(), brandSupplier.getSupplier().getId());

		if (existBrandSupplier != null)
			return new ResponseEntity<>("Brand Supplier has already existed. Please choose new brand and supplier. ",
					HttpStatus.NOT_ACCEPTABLE);
		brandSupplier.setCreatedDate(new Date());
		brandSupplier.setUpdatedDate(new Date());
		try {
			brandSupplierService.createOrUpdate(brandSupplier);
			return new ResponseEntity<>("New brand supplier has created successfully! ID is  " + brandSupplier.getId(),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);

		}
	}

	@PutMapping(value = URI.UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody BrandSupplier brandSupplier) {

		if (brandSupplier.getId() == null || !StringUtils.hasText(brandSupplier.getId()))
			return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);

		BrandSupplier existBrandSupplier = brandSupplierService.findBrandSupplierById(brandSupplier.getId());

		if (existBrandSupplier == null)
			return new ResponseEntity<>("Brand Supplier not found for brandsupplier ID " + brandSupplier.getId(),
					HttpStatus.NOT_FOUND);

		if (StringUtils.hasText(brandSupplier.getBrand().getId())) {

			if (brandService.findBrandById(brandSupplier.getBrand().getId()) == null)
				return new ResponseEntity<>("Brand has not in the system. Bad request.", HttpStatus.BAD_REQUEST);

			existBrandSupplier.setBrand(brandSupplier.getBrand());
		}

		if (StringUtils.hasText(brandSupplier.getSupplier().getId())) {

			if (supplierService.findSupplierById(brandSupplier.getSupplier().getId()) == null)
				return new ResponseEntity<>("Supplier has not in the system. Bad request.", HttpStatus.BAD_REQUEST);
			
			existBrandSupplier.setSupplier(brandSupplier.getSupplier());
		}

		existBrandSupplier.setUpdatedDate(new Date());
		try {
			brandSupplierService.createOrUpdate(existBrandSupplier);
			return new ResponseEntity<>(
					"Barand Supplier details has been updated for brandID : " + brandSupplier.getId(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					"Barand Supplier details not modified for brandID : " + brandSupplier.getId() + " due to :" + e,
					HttpStatus.NOT_MODIFIED);
		}

	}

	@GetMapping(value = URI.BRAND_SUP_FIND_ALL)
	public List<BrandSupplier> findAllBrand() {
		return brandSupplierService.findAllBrandSuuplier();
	}

	@GetMapping(value = URI.BRAND_SUP_BY_ID)
	public BrandSupplier findBrandbyId(@PathVariable("brandSupplierId") String brandSupplierId) {

		if (brandSupplierId == null || StringUtils.isEmpty(brandSupplierId))
			return null;

		return brandSupplierService.findBrandSupplierById(brandSupplierId);
	}

	@DeleteMapping(value = URI.BRAND_SUP_DELETE_BY_ID)
	public ResponseEntity<Object> deleteBrandSupplierById(@PathVariable("brandSupplierId") String brandSupplierId) {

		if (brandSupplierId == null || !StringUtils.hasText(brandSupplierId))
			return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);

		BrandSupplier brandsupplier = brandSupplierService.findBrandSupplierById(brandSupplierId);

		if (brandsupplier == null)
			return new ResponseEntity<>("Resource Not found", HttpStatus.NOT_FOUND);

		try {
			brandSupplierService.deleteBrandSupplierById(brandSupplierId);
			return new ResponseEntity<>("Brand supplier has deleted successfully!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
		}

	}

	@GetMapping(value = URI.BRAND_SUP_BY_BRAND_NAME)
	public List<BrandSupplier> findBrandSuppliersByBrandName(@PathVariable("name") String name) {
		return brandSupplierService.findBrandSuppliersByBrandName(name);
	}

	@GetMapping(value = URI.BRAND_SUP_BY_SUPPLIER_NAME)
	public List<BrandSupplier> findBrandSuppliersBySupplierName(@PathVariable("name") String name) {
		return brandSupplierService.findBrandSuppliersBySupplierName(name);
	}

}
