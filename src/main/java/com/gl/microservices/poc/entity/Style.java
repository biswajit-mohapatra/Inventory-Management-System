package com.gl.microservices.poc.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.gl.microservices.poc.util.InventoryType.Color;
import com.gl.microservices.poc.util.InventoryType.StyleState;

@Document(collection = "style")
public class Style {
	@Id
	private String id;

	@DBRef(db = "product", lazy = false)
	@Field(value = "product_id")
	private Product product;

	@Field(value = "color")
	private Color color;

	@Field(value = "style_number")
	private String styleNumber;

	@Field(value = "state")
	private StyleState state;

	@Field(value = "price")
	private BigDecimal price;

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getStyleNumber() {
		return styleNumber;
	}

	public void setStyleNumber(String styleNumber) {
		this.styleNumber = styleNumber;
	}

	public StyleState getState() {
		return state;
	}

	public void setState(StyleState state) {
		this.state = state;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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
		return "Style [id=" + id + ", product=" + product + ", color=" + color + ", styleNumber=" + styleNumber
				+ ", state=" + state + ", price=" + price + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + "]";
	}

}
