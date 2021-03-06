package com.gl.microservices.poc.controller;

import com.gl.microservices.poc.model.PriceModel;
import javax.xml.ws.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/pricing")
public class PricingController {

  @GetMapping(value = "/product-price/product-id/{productId}")
  public ResponseEntity<Object> getProductPrice(@PathVariable("productId") Integer productId) {

    if (productId == null) {
      return ResponseEntity.badRequest().body("ProductId can nt be null");
    }
    log.info("Product validation has been completed");
    return ResponseEntity.ok(new PriceModel().setPriceId(1).setPriceSymbol("$"));
  }
}
