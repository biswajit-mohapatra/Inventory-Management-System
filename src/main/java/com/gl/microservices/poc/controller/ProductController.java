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

import com.gl.microservices.poc.entity.Product;
import com.gl.microservices.poc.service.BrandSupplierService;
import com.gl.microservices.poc.service.ProductService;
import com.gl.microservices.poc.service.ProductTypeService;
import com.gl.microservices.poc.util.URI;

@RestController
@RequestMapping(value = URI.PRODUCT)
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	BrandSupplierService brandSupplierService;

	@Autowired
	ProductTypeService productTypeService;

	@PostMapping(value = URI.CREATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody Product product) {

		if (!StringUtils.hasText(product.getName()))
			return new ResponseEntity<>("Product name can't be Blank. Bad request.", HttpStatus.BAD_REQUEST);

		Product existProduct = productService.findProductByName(product.getName());

		if (existProduct != null)
			return new ResponseEntity<>(
					"Product name " + product.getName() + " has already existed. Please choose new product Name. ",
					HttpStatus.NOT_ACCEPTABLE);

		if (!StringUtils.hasText(product.getBrandSupplier().getId()))
			return new ResponseEntity<>("Brand supplier ID should not be blank. Bad request.", HttpStatus.BAD_REQUEST);

		if (!StringUtils.hasText(product.getProductType().getId()))
			return new ResponseEntity<>("Product type ID should not be blank. Bad request.", HttpStatus.BAD_REQUEST);

		if (brandSupplierService.findBrandSupplierById(product.getBrandSupplier().getId()) == null)
			return new ResponseEntity<>("Brand  supplier has not in the system. Bad request.", HttpStatus.BAD_REQUEST);

		if (productTypeService.findProductTypeById(product.getProductType().getId()) == null)
			return new ResponseEntity<>("Product type has not in the system. Bad request.", HttpStatus.BAD_REQUEST);

		if (product.getState() == null)
			return new ResponseEntity<>("State should not be null. Bad request.", HttpStatus.BAD_REQUEST);

		try {

			product.setCreatedDate(new Date());
			product.setUpdatedDate(new Date());

			productService.saveOrUpdate(product);
			return new ResponseEntity<>("New Product has created successfully! ID is  " + product.getId(),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);

		}
	}

	@PutMapping(value = URI.UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody Product product) {

		if (product.getId() == null || !StringUtils.hasText(product.getId()))
			return new ResponseEntity<>("ProductID should not be null.", HttpStatus.BAD_REQUEST);

		Product existProduct = productService.findProductById(product.getId());

		if (existProduct == null)
			return new ResponseEntity<>("Product not found with brandID " + product.getId(), HttpStatus.NOT_FOUND);

		if (StringUtils.hasText(product.getName())) {
			if (!existProduct.getName().equalsIgnoreCase(existProduct.getName()))
				return new ResponseEntity<>("Product name should be same while updating...", HttpStatus.CONFLICT);
		}

		if (StringUtils.hasText(product.getBrandSupplier().getId())) {
			if (brandSupplierService.findBrandSupplierById(product.getBrandSupplier().getId()) == null)
				return new ResponseEntity<>("Brand  supplier has not in the system. Bad request.",
						HttpStatus.BAD_REQUEST);
			existProduct.setBrandSupplier(product.getBrandSupplier());

		}
		if (StringUtils.hasText(product.getProductType().getId())) {
			if (productTypeService.findProductTypeById(product.getProductType().getId()) == null)
				return new ResponseEntity<>("Product type has not in the system. Bad request.", HttpStatus.BAD_REQUEST);
			existProduct.setProductType(product.getProductType());
		}

		if (product.getState() != null)
			existProduct.setState(product.getState());

		existProduct.setUpdatedDate(new Date());
		try {
			productService.saveOrUpdate(existProduct);
			return new ResponseEntity<>("Prduct details has been updated for productID : " + product.getId(),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					"Product details not modified for brandID : " + product.getId() + " due to :" + e,
					HttpStatus.NOT_MODIFIED);
		}

	}

	@GetMapping(value = URI.PRODUCT_FIND_ALL)
	public List<Product> findAllProduct() {
		return productService.findAllProduct();

	}

	@GetMapping(value = URI.PRODUCT_BY_ID)
	public Product findProductbyId(@PathVariable("productId") String productId) {

		if (productId == null || StringUtils.isEmpty(productId))
			return null;

		return productService.findProductById(productId);
	}

	@DeleteMapping(value = URI.PRODUCT_DELETE_BY_ID)
	public ResponseEntity<Object> deleteProductById(@PathVariable("productId") String productId) {

		if (productId == null || !StringUtils.hasText(productId))
			return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);

		Product product = productService.findProductById(productId);

		if (product == null)
			return new ResponseEntity<>("Resource Not found", HttpStatus.NOT_FOUND);

		try {
			productService.deleteProductById(productId);

			return new ResponseEntity<>("Product has deleted successfully!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
		}

	}

}
