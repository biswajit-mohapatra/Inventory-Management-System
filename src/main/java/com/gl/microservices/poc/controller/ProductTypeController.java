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

import com.gl.microservices.poc.entity.ProductType;
import com.gl.microservices.poc.service.ProductTypeService;
import com.gl.microservices.poc.util.URI;

@RestController
@RequestMapping(value = URI.PRODUCT_TYPE)
public class ProductTypeController {

	@Autowired
	ProductTypeService productTypeService;

	@PostMapping(value = URI.CREATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody ProductType productType) {

		if (!StringUtils.hasText(productType.getName()))
			return new ResponseEntity<>("Product type name can't be Blank. Bad request.", HttpStatus.BAD_REQUEST);

		ProductType existProductType = productTypeService.findProductTypeByName(productType.getName());

		if (existProductType != null)
			return new ResponseEntity<>("Product type name " + productType.getName()
			+ " has already existed. Please choose new product type Name. ", HttpStatus.NOT_ACCEPTABLE);

		productType.setCreatedDate(new Date());
		productType.setUpdateDate(new Date());
		try {
			productTypeService.saveOrUpdate(productType);
			return new ResponseEntity<>(
					"New Product type has created successfully! ID is  " + productType.getId(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);

		}
	}

	@PutMapping(value = URI.UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody ProductType productType) {

		if (productType.getId() == null || !StringUtils.hasText(productType.getId()))
			return new ResponseEntity<>("ProductTypeId is Null.", HttpStatus.BAD_REQUEST);

		ProductType existProductType = productTypeService.findProductTypeById(productType.getId());

		if (existProductType == null)
			return new ResponseEntity<>("Product type not found for productTypeId " + productType.getId(),
					HttpStatus.NOT_FOUND);

		if (StringUtils.hasText(productType.getName())) {
			
			ProductType existProductTypeByName = productTypeService.findProductTypeByName(productType.getName());
			
			if (!existProductTypeByName.getId().equals(existProductType.getId()))
				return new ResponseEntity<>("Product type name is exist with that name.",
						HttpStatus.CONFLICT);
			else
				existProductType.setName(productType.getName());
		}

		existProductType.setUpdateDate(new Date());
		try {
			productTypeService.saveOrUpdate(existProductType);
			return new ResponseEntity<>(
					"Product type details has been updated for productTypeId : " + existProductType.getId(),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					"Product type details not modified for productTypeId : " + productType.getId() + " due to :" + e,
					HttpStatus.NOT_MODIFIED);
		}

	}

	@GetMapping(value = URI.PRODUCT_TYPE_FIND_ALL)
	public List<ProductType> findAllProductType() {
		return productTypeService.findAllProductType();
	}

	@GetMapping(value = URI.PRODUCT_TYPE_BY_ID)
	public ProductType findProductTypeById(@PathVariable("productTypeId") String productTypeId) {

		if (productTypeId == null || StringUtils.isEmpty(productTypeId))
			return null;

		return productTypeService.findProductTypeById(productTypeId);
	}

	@DeleteMapping(value = URI.PRODUCT_TYPE_DELETE_BY_ID)
	public ResponseEntity<Object> deleteProductTypeById(@PathVariable("productTypeId") String productTypeId) {

		if (productTypeId == null || !StringUtils.hasText(productTypeId))
			return new ResponseEntity<>("ProductTypeId is null.", HttpStatus.BAD_REQUEST);

		ProductType productType = productTypeService.findProductTypeById(productTypeId);

		if (productType == null)
			return new ResponseEntity<>("Resource Not found", HttpStatus.NOT_FOUND);

		try {
			productTypeService.deleteProductTypeById(productTypeId);
			return new ResponseEntity<>("Product type has deleted successfully!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
		}

	}

}
