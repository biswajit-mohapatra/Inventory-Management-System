package com.gl.microservices.poc.entity;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.gl.microservices.poc.util.InventoryType.Size;
import com.gl.microservices.poc.util.InventoryType.StockStatus;

@Document(collection = "stock")
public class Stock {

	@Id
	private String id;

	@DBRef(db = "style", lazy = false)
	@Field(value = "style_id")
	private Style style;

	@Field(value = "size")
	private Size size;

	@Field(value = "status")
	private StockStatus status;

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

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public StockStatus getStatus() {
		return status;
	}

	public void setStatus(StockStatus status) {
		this.status = status;
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
		return "Stock [id=" + id + ", style=" + style + ", size=" + size + ", status=" + status + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + "]";
	}
}
