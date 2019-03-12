package com.gl.microservices.poc;

import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.gl.microservices.poc.entity.Brand;
import com.gl.microservices.poc.repository.BrandRepository;
import com.gl.microservices.poc.service.BrandService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrandTest {

  @Autowired
  BrandRepository brandRepository;
  
  @Autowired
  BrandService brandService;

  // @Test
  public void createBrandTest() {
    Brand brand = new Brand();
    brand.setName("Nike123");
    brand.setPublicEmail("brand@nike.com");
    brand.setPublicePhone("8800755977");
    brand.setCreatedDate(new Date());
    brand.setUpdateDate(new Date());
    brandRepository.save(brand);
    Assert.assertNotNull(brand.getId());
  }

  // @Test
  public void getBrandsTest() {

    List<Brand> brands = brandRepository.findAll();
    Assert.assertNotNull(brands);
    Assert.assertTrue(brands.size() > 0);
  }

  @Test
  public void getAllBrandsCacheTest() {
    long start = System.currentTimeMillis();
    List<Brand> brands =  brandService.findAllBrand();
    Assert.assertNotNull(brands);
    Assert.assertTrue(brands.size() > 0);
    System.out.println("brands>>>>" + brands);
    long end = System.currentTimeMillis();
    System.out.println("For first time took : " + ((end - start) / 1000 + " sec."));

    start = System.currentTimeMillis();
    brands = brandService.findAllBrand();
    Assert.assertNotNull(brands);
    Assert.assertTrue(brands.size() > 0);
    System.out.println("brands>>>>" + brands);
    end = System.currentTimeMillis();
    System.out.println("Second time onward took : " + ((end - start) / 1000 + " sec."));
    
    start = System.currentTimeMillis();
    brands = brandService.findAllBrand();
    Assert.assertNotNull(brands);
    Assert.assertTrue(brands.size() > 0);
    System.out.println("brands>>>>" + brands);
    end = System.currentTimeMillis();
    System.out.println("Third time onward took : " + ((end - start) / 1000 + " sec."));

  }

}
