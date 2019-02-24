package com.gl.microservices.poc.service;

import java.util.List;

import com.gl.microservices.poc.entity.Stock;

public interface StockService {

	void saveOrUpdate(Stock stock);

	Stock findStockById(String stockId);

	List<Stock> findAllStock();

	void deleteStockById(String stockId);
}
