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

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrandTest {

@Autowired
BrandRepository brandRepository;
	
@Test
public void createBrandTest(){
	Brand brand = new Brand();
	brand.setName("Nike123");
	brand.setPublicEmail("brand@nike.com");
	brand.setPublicePhone("8800755977");
	brand.setCreatedDate(new Date());
	brand.setUpdateDate(new Date());
	brandRepository.save(brand);
	Assert.assertNotNull(brand.getId());
}

@Test
public void getBrandsTest(){
	
	List<Brand> brands= brandRepository.findAll();
	Assert.assertNotNull(brands);
	Assert.assertTrue(brands.size() > 0);
}

}
