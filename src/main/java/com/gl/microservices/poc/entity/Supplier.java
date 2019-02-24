package com.gl.microservices.poc.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="supplier")
public class Supplier implements Serializable {
	
private static final long serialVersionUID = 1L;

@Id
private String id;
private String name;
private String contactName;
private String contactEmail;
private Date createdDate;
private Date updateDate;
private String isEnabled;

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
public String getContactName() {
	return contactName;
}
public void setContactName(String contactName) {
	this.contactName = contactName;
}
public String getContactEmail() {
	return contactEmail;
}
public void setContactEmail(String contactEmail) {
	this.contactEmail = contactEmail;
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
public String getIsEnabled() {
	return isEnabled;
}
public void setIsEnabled(String isEnabled) {
	this.isEnabled = isEnabled;
}

@Override
public String toString() {
	return "Supplier [id=" + id + ", name=" + name + ", contactName=" + contactName + ", contactEmail=" + contactEmail
			+ ", createdDate=" + createdDate + ", updateDate=" + updateDate + ", isEnabled=" + isEnabled + "]";
}

}
