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

import com.gl.microservices.poc.entity.Stock;
import com.gl.microservices.poc.service.StockService;
import com.gl.microservices.poc.service.StyleService;
import com.gl.microservices.poc.util.URI;

@RestController
@RequestMapping(value = URI.STOCK)
public class StockController {

	@Autowired
	StockService stockService;

	@Autowired
	StyleService styleService;

	@PostMapping(value = URI.CREATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody Stock stock) {

		if (!StringUtils.hasText(stock.getStyle().getId()))
			return new ResponseEntity<>("Style ID should not be Blank. Bad request.", HttpStatus.BAD_REQUEST);

		if (styleService.findStyleById(stock.getStyle().getId()) == null)
			return new ResponseEntity<>("Style has not in the system. Bad request.", HttpStatus.BAD_REQUEST);

		if (stock.getSize() == null)
			return new ResponseEntity<>("Size should not be null. Bad request.", HttpStatus.BAD_REQUEST);

		if (stock.getStatus() == null)
			return new ResponseEntity<>("Status should not be null. Bad request.", HttpStatus.BAD_REQUEST);

		try {
			stock.setCreatedDate(new Date());
			stock.setUpdatedDate(new Date());

			stockService.saveOrUpdate(stock);
			return new ResponseEntity<>("New Stock has created successfully! ID is  " + stock.getId(),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);

		}
	}

	@PutMapping(value = URI.UPDATE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody Stock stock) {

		if (stock.getId() == null || !StringUtils.hasText(stock.getId()))
			return new ResponseEntity<>("StockId should not be null.", HttpStatus.BAD_REQUEST);

		Stock existStock = stockService.findStockById(stock.getId());

		if (existStock == null)
			return new ResponseEntity<>("Stock not found for stockID " + stock.getId(), HttpStatus.NOT_FOUND);

		if (StringUtils.hasText(stock.getStyle().getId())) {
			if (!(existStock.getStyle().getId().equals(stock.getStyle().getId())))
				return new ResponseEntity<>("Style ID will never change. Bad request.", HttpStatus.BAD_REQUEST);

		}

		if (stock.getSize() != null)
			existStock.setSize(stock.getSize());

		if (stock.getStatus() != null)
			existStock.setStatus(stock.getStatus());

		existStock.setUpdatedDate(new Date());

		try {
			stockService.saveOrUpdate(existStock);
			return new ResponseEntity<>("Stock details has been updated for productID : " + stock.getId(),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Stock details of StockID " + stock.getId() + " was not modified due to :" + e,
					HttpStatus.NOT_MODIFIED);
		}

	}

	@GetMapping(value = URI.STOCK_FIND_ALL)
	public List<Stock> findAllStock() {
		return stockService.findAllStock();

	}

	@GetMapping(value = URI.STOCK_BY_ID)
	public Stock findStockbyId(@PathVariable("stockId") String stockId) {

		if (stockId == null || StringUtils.isEmpty(stockId))
			return null;

		return stockService.findStockById(stockId);
	}

	@DeleteMapping(value = URI.STOCK_DELETE_BY_ID)
	public ResponseEntity<Object> deleteStockById(@PathVariable("stockId") String stockId) {

		if (stockId == null || !StringUtils.hasText(stockId))
			return new ResponseEntity<>("StockID should not be null.Bad request.", HttpStatus.BAD_REQUEST);

		Stock stock = stockService.findStockById(stockId);

		if (stock == null)
			return new ResponseEntity<>("Resource Not found", HttpStatus.NOT_FOUND);

		try {
			stockService.deleteStockById(stockId);

			return new ResponseEntity<>("Stock has deleted successfully!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
		}

	}

}
