package com.gl.microservices.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.microservices.poc.entity.Style;
import com.gl.microservices.poc.repository.StyleRepository;

@Service
public class StyleServiceImpl implements StyleService {

	@Autowired
	StyleRepository styleRepository;

	@Override
	public void saveOrUpdate(Style style) {
		styleRepository.save(style);
	}

	@Override
	public Style findStyleById(String styleId) {
		return styleRepository.findOne(styleId);
	}

	@Override
	public List<Style> findAllStyle() {
		return styleRepository.findAll();
	}

	@Override
	public void deleteStyleById(String styleId) {
		styleRepository.delete(styleId);
	}

	@Override
	public Style findStyleByStyleNumber(String name) {
		return styleRepository.findByStyleNumber(name);
	}

}
