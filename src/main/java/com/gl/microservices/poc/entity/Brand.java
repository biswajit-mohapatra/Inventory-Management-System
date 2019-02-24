package com.gl.microservices.poc.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="brand")
public class Brand {
@Id
private String id;
private String name;
private String brandUrl;
private String publicePhone;
private String customerCareNumber;
private String publicEmail;
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
public String getBrandUrl() {
	return brandUrl;
}
public void setBrandUrl(String brandUrl) {
	this.brandUrl = brandUrl;
}
public String getPublicePhone() {
	return publicePhone;
}
public void setPublicePhone(String publicePhone) {
	this.publicePhone = publicePhone;
}
public String getCustomerCareNumber() {
	return customerCareNumber;
}
public void setCustomerCareNumber(String customerCareNumber) {
	this.customerCareNumber = customerCareNumber;
}
public String getPublicEmail() {
	return publicEmail;
}
public void setPublicEmail(String publicEmail) {
	this.publicEmail = publicEmail;
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
	return "Brand [Id=" + id + ", name=" + name + ", brandUrl=" + brandUrl + ", publicePhone=" + publicePhone
			+ ", customerCareNumber=" + customerCareNumber + ", publicEmail=" + publicEmail + ", createdDate="
			+ createdDate + ", updateDate=" + updateDate + "]";
}

}
