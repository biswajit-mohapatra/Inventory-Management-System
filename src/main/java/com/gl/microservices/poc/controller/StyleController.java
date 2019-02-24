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

import com.gl.microservices.poc.entity.Style;
import com.gl.microservices.poc.service.ProductService;
import com.gl.microservices.poc.service.StyleService;
import com.gl.microservices.poc.util.URI;

@RestController
@RequestMapping(value = URI.STYLE)
public class StyleController {

	@Autowired
	StyleService styleService;

	@Autowired
	ProductService productService;

	@PostMapping(value = URI.CREATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody Style style) {

		if (!StringUtils.hasText(style.getProduct().getId()))
			return new ResponseEntity<>("Product ID should not be Blank. Bad request.", HttpStatus.BAD_REQUEST);

		if (productService.findProductById(style.getProduct().getId()) == null)
			return new ResponseEntity<>("Product has not in the system. Bad request.", HttpStatus.BAD_REQUEST);

		if (style.getColor() == null)
			return new ResponseEntity<>("Color should not be null. Bad request.", HttpStatus.BAD_REQUEST);

		if (style.getState() == null)
			return new ResponseEntity<>("State should not be null. Bad request.", HttpStatus.BAD_REQUEST);

		if (style.getPrice() == null)
			return new ResponseEntity<>("Price should not be null. Bad request.", HttpStatus.BAD_REQUEST);

		style.setCreatedDate(new Date());
		style.setUpdatedDate(new Date());

		try {
			styleService.saveOrUpdate(style);
			return new ResponseEntity<>("New Style has created successfully! ID is  " + style.getId(),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);

		}
	}

	@PutMapping(value = URI.UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody Style style) {

		if (style.getId() == null || !StringUtils.hasText(style.getId()))
			return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);

		Style existStyle = styleService.findStyleById(style.getId());

		if (existStyle == null)
			return new ResponseEntity<>("Style not found with StyleID " + style.getId(), HttpStatus.NOT_FOUND);

		if (StringUtils.hasText(style.getProduct().getId())) {

			if (productService.findProductById(style.getProduct().getId()) == null)
				return new ResponseEntity<>("Product has not in the system. Bad request.", HttpStatus.BAD_REQUEST);

			if (!existStyle.getProduct().getId().equals(style.getProduct().getId())) {
				return new ResponseEntity<>("Product can't be updated.", HttpStatus.BAD_REQUEST);
			}

		}
		if (style.getColor() != null)
			existStyle.setColor(style.getColor());

		if (style.getState() != null)
			existStyle.setState(style.getState());

		if (style.getPrice() != null)
			existStyle.setPrice(style.getPrice());

		existStyle.setUpdatedDate(new Date());

		try {
			styleService.saveOrUpdate(existStyle);
			return new ResponseEntity<>("Style details has been updated for styleID : " + style.getId(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Style details not modified for styleID : " + style.getId() + " due to :" + e,
					HttpStatus.NOT_MODIFIED);
		}

	}

	@GetMapping(value = URI.STYLE_FIND_ALL)
	public List<Style> findAllStyle() {
		return styleService.findAllStyle();
	}

	@GetMapping(value = URI.STYLE_BY_ID)
	public Style findStyleById(@PathVariable("styleId") String styleId) {

		if (styleId == null || StringUtils.isEmpty(styleId))
			return null;

		return styleService.findStyleById(styleId);
	}

	@DeleteMapping(value = URI.STYLE_DELETE_BY_ID)
	public ResponseEntity<Object> deleteStyleById(@PathVariable("styleId") String styleId) {

		if (styleId == null || !StringUtils.hasText(styleId))
			return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);

		Style style = styleService.findStyleById(styleId);

		if (style == null)
			return new ResponseEntity<>("Resource Not found", HttpStatus.NOT_FOUND);

		try {
			styleService.deleteStyleById(styleId);
			return new ResponseEntity<>("Style has deleted successfully!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
		}
	}

}
