package com.gl.microservices.poc.service;

import java.util.List;

import com.gl.microservices.poc.entity.Style;

public interface StyleService {

	void saveOrUpdate(Style style);

	Style findStyleById(String styleId);

	List<Style> findAllStyle();

	void deleteStyleById(String styleId);

	Style findStyleByStyleNumber(String name);
}
