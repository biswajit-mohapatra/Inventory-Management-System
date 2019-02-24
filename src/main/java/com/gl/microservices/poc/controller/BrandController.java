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

import com.gl.microservices.poc.entity.Brand;
import com.gl.microservices.poc.service.BrandService;
import com.gl.microservices.poc.util.URI;

@RestController
@RequestMapping(value = URI.BRAND)
public class BrandController {

	@Autowired
	BrandService brandService;

	@PostMapping(value = URI.CREATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody Brand brand) {

		if (!StringUtils.hasText(brand.getName()))
			return new ResponseEntity<>("Brand name can't be Blank. Bad request.", HttpStatus.BAD_REQUEST);

		Brand ExistBrand = brandService.findBrandByName(brand.getName());

		if (ExistBrand != null)
			return new ResponseEntity<>(
					"Brand name " + brand.getName() + " has already existed. Please choose new brand Name. ",
					HttpStatus.NOT_ACCEPTABLE);

		brand.setCreatedDate(new Date());
		brand.setUpdateDate(new Date());
		try {
			brandService.createOrUpdate(brand);
			return new ResponseEntity<>("New brand has created successfully! ID is  " + brand.getId(),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);

		}
	}

	@PutMapping(value = URI.UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody Brand brand) {

		if (brand.getId() == null || !StringUtils.hasText(brand.getId()))
			return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);

		Brand existBrand = brandService.findBrandById(brand.getId());

		if (existBrand == null)
			return new ResponseEntity<>("Brand Not found with brandID " + brand.getId(), HttpStatus.NOT_FOUND);

		if (StringUtils.hasText(brand.getName())) {
			if (!existBrand.getName().equalsIgnoreCase(brand.getName()))
				return new ResponseEntity<>("Brand name should be same while updating...", HttpStatus.CONFLICT);
		}

		if (StringUtils.hasText(brand.getBrandUrl()))
			existBrand.setBrandUrl(brand.getBrandUrl());

		if (StringUtils.hasText(brand.getPublicEmail()))
			existBrand.setPublicEmail(brand.getPublicEmail());

		if (StringUtils.hasText(brand.getCustomerCareNumber()))
			existBrand.setCustomerCareNumber(brand.getCustomerCareNumber());

		existBrand.setUpdateDate(new Date());
		try {
			brandService.createOrUpdate(existBrand);
			return new ResponseEntity<>("Barand details has been updated for brandID : " + brand.getId(),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Barand details not modified for brandID : " + brand.getId() + " due to :" + e,
					HttpStatus.NOT_MODIFIED);
		}

	}

	@GetMapping(value = URI.BRAND_FIND_ALL)
	public List<Brand> findAllBrand() {
		return brandService.findAllBrand();
	}

	@GetMapping(value = URI.BRAND_BY_ID)
	public Brand findBrandById(@PathVariable("brandId") String brandId) {

		if (brandId == null || StringUtils.isEmpty(brandId))
			return null;

		return brandService.findBrandById(brandId);
	}

	@DeleteMapping(value = URI.BRAND_DELETE_BY_ID)
	public ResponseEntity<Object> deleteBrandById(@PathVariable("brandId") String brandId) {

		if (brandId == null || !StringUtils.hasText(brandId))
			return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);

		Brand brand = brandService.findBrandById(brandId);

		if (brand == null)
			return new ResponseEntity<>("Resource Not found", HttpStatus.NOT_FOUND);

		try {
			brandService.deleteBrandById(brandId);
			return new ResponseEntity<>("Brand has deleted successfully!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
		}

	}

}
