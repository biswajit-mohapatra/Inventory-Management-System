package com.gl.microservices.poc.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="product_type")
public class ProductType {
@Id
private String id;
private String name;
private Date createdDate;
private Date updateDate;
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
public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
public Date getUpdateDate() {
	return updateDate;
}
public void setUpdateDate(Date updateDate) {
	this.updateDate = updateDate;
}
@Override
public String toString() {
	return "ProductType [id=" + id + ", name=" + name + ", createdDate=" + createdDate + ", updateDate=" + updateDate
			+ "]";
}

}
