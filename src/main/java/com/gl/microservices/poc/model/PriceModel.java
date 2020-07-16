package com.gl.microservices.poc.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PriceModel {
  private Integer priceId;
  private String priceSymbol;

}
