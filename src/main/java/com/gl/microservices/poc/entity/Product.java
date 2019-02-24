package com.gl.microservices.poc.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.gl.microservices.poc.util.InventoryType.ProductState;

@Document(collection = "product")
public class Product {

	@Id
	@Field(value = "product_id")
	private String id;

	private String name;

	@DBRef(db = "brand_supplier", lazy = false)
	@Field(value = "brand_supplier_id")
	private BrandSupplier brandSupplier;

	@DBRef(db = "product_type", lazy = false)
	@Field(value = "product_type_id")
	private ProductType productType;

	@Field(value = "state")
	private ProductState state;

	@Field(value = "created_date")
	private Date createdDate;

	@Field(value = "updated_date")
	private Date updatedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BrandSupplier getBrandSupplier() {
		return brandSupplier;
	}

	public void setBrandSupplier(BrandSupplier brandSupplier) {
		this.brandSupplier = brandSupplier;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public ProductState getState() {
		return state;
	}

	public void setState(ProductState state) {
		this.state = state;
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
		return "Product [id=" + id + ", name=" + name + ", brandSupplier=" + brandSupplier + ", productType="
				+ productType + ", state=" + state + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ "]";
	}
}
