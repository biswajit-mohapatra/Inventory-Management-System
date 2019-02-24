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

import com.gl.microservices.poc.entity.Supplier;
import com.gl.microservices.poc.service.SupplierService;
import com.gl.microservices.poc.util.URI;

@RestController
@RequestMapping(value=URI.SUPPLIER)
public class SupplierController {

	@Autowired
	SupplierService supplierService;
		
	@PostMapping(value = URI.CREATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody Supplier supplier) {
		
		if (!StringUtils.hasText(supplier.getName()))
			return new ResponseEntity<>("Supplier name can't be Blank. Bad request.", HttpStatus.BAD_REQUEST);

		Supplier persistSupplier = supplierService.findSupplierByName(supplier.getName());

		if (persistSupplier != null)
			return new ResponseEntity<>(
					"Supplier name " + supplier.getName() + " has already existed. Please choose new Supplier name. ",
					HttpStatus.NOT_ACCEPTABLE);
		supplier.setIsEnabled("Yes");
		supplier.setCreatedDate(new Date());
		supplier.setUpdateDate(new Date());
		try {
			supplierService.create(supplier);
			return new ResponseEntity<>("New Supplier has created successfully! ID is  " + supplier.getId(),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);

		}
	}

	@PutMapping(value = URI.UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody Supplier supplier) {

		if (supplier.getId() == null || !StringUtils.hasText(supplier.getId()))
			return new ResponseEntity<Object>("Bad request", HttpStatus.BAD_REQUEST);

		Supplier persistSupplier = supplierService.findSupplierById(supplier.getId());

		if (persistSupplier == null)
			return new ResponseEntity<Object>("Supplier not found with brandID " + supplier.getId(),
					HttpStatus.NOT_FOUND);

		if (StringUtils.hasText(supplier.getName())) {
			if (!persistSupplier.getName().equalsIgnoreCase(supplier.getName()))
				return new ResponseEntity<Object>("Supplier name should be same while updating...",
						HttpStatus.CONFLICT);
		}

		if (StringUtils.hasText(supplier.getContactEmail()))
			persistSupplier.setContactEmail(supplier.getContactEmail());

		if (StringUtils.hasText(supplier.getContactName()))
			persistSupplier.setContactName(supplier.getContactName());

		persistSupplier.setUpdateDate(new Date());
		try {
			supplierService.create(persistSupplier);
			return new ResponseEntity<Object>("Supplier details has been updated for supplierID : " + supplier.getId(),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(
					"Supplier details not modified for supplierID : " + supplier.getId() + " due to :" + e,
					HttpStatus.NOT_MODIFIED);
		}

	}

	@GetMapping(value = URI.SUPPLIER_FIND_ALL)
	public List<Supplier> findAllSupplier() {
		return supplierService.findAllSupplier();
	}

	@GetMapping(value = URI.SUPPLIER_BY_ID)
	public Supplier findSupplierbyId(@PathVariable("supplierId") String supplierId) {

		if (supplierId == null || StringUtils.isEmpty(supplierId))
			return null;

		return supplierService.findSupplierById(supplierId);
	}

	@DeleteMapping(value = URI.SUPPLIER_DELETE_BY_ID)
	public ResponseEntity<Object> deleteSupplierById(@PathVariable("supplierId") String supplierId) {

		if (supplierId == null || !StringUtils.hasText(supplierId))
			return new ResponseEntity<Object>("Bad request", HttpStatus.BAD_REQUEST);

		Supplier supplier = supplierService.findSupplierById(supplierId);

		if (supplier == null)
			return new ResponseEntity<Object>("Supplier Not found", HttpStatus.NOT_FOUND);

		try {
			supplierService.deleteSupplierById(supplierId);
			return new ResponseEntity<Object>("Supplier has deleted successfully!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.EXPECTATION_FAILED);
		}

	}

}
