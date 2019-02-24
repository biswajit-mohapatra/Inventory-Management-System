package com.gl.microservices.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.microservices.poc.entity.Brand;
import com.gl.microservices.poc.entity.Stock;
import com.gl.microservices.poc.repository.BrandRepository;
import com.gl.microservices.poc.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;

	@Override
	public void saveOrUpdate(Stock stock) {
		stockRepository.save(stock);	
	}

	@Override
	public Stock findStockById(String stockId) {
		return stockRepository.findOne(stockId);
	}

	@Override
	public List<Stock> findAllStock() {
		return stockRepository.findAll();
	}

	@Override
	public void deleteStockById(String stockId) {
		stockRepository.delete(stockId);		
	}

}
