package com.gl.microservices.poc.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "brand_supplier")
public class BrandSupplier {
	@Id
	private String id;
	@DBRef(db = "brand", lazy = false)
	@Field(value="brand_id")
	private Brand brand;
	@DBRef(db = "supplier", lazy = false)
	@Field(value="supplier_id")
	private Supplier supplier;
	
	@Field(value="created_date")
	private Date createdDate;
	
	@Field(value="updated_date")
	private Date updatedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "BrandSupplier [id=" + id + ", brand=" + brand + ", supplier=" + supplier + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + "]";
	}
	
}
