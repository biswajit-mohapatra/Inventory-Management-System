package com.gl.microservices.poc;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.gl.microservices.poc.entity.Supplier;
import com.gl.microservices.poc.repository.SupplierRepository;
import com.gl.microservices.poc.service.SupplierService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupplierTest {

  @Autowired
  SupplierRepository supplierRepository;

  @Autowired
  SupplierService supplierService;

  @Test
  public void getAllSuppliersTest() {
    System.out.println("All Suppliers Test");
    List<Supplier> suppliers = supplierRepository.findAll();
    Assert.assertNotNull(suppliers);
    Assert.assertTrue(suppliers.size() > 0);
  }

  @Test
  public void getAllSupplierCacheTest() {
    System.out.println("All Suppliers Cache Test");
    long start = System.currentTimeMillis();
    List<Supplier> suppliers = supplierService.findAllSupplier();
    Assert.assertNotNull(suppliers);
    Assert.assertTrue(suppliers.size() > 0);
    System.out.println("suppliers>>>>" + suppliers);
    long end = System.currentTimeMillis();
    System.out.println("For first time took : " + ((end - start) / 1000 + " sec."));

    start = System.currentTimeMillis();
    suppliers = supplierService.findAllSupplier();
    Assert.assertNotNull(suppliers);
    Assert.assertTrue(suppliers.size() > 0);
    System.out.println("suppliers>>>>" + suppliers);
    end = System.currentTimeMillis();
    System.out.println("Second time took : " + ((end - start) / 1000 + " sec."));

    start = System.currentTimeMillis();
    suppliers = supplierService.findAllSupplier();
    Assert.assertNotNull(suppliers);
    Assert.assertTrue(suppliers.size() > 0);
    System.out.println("suppliers>>>>" + suppliers);
    end = System.currentTimeMillis();
    System.out.println("Third time took : " + ((end - start) / 1000 + " sec."));

  }

  @Test
  public void getSupplierByIdCacheTest() {

    System.out.println("Supplier By Id Cache Test");
    long start = System.currentTimeMillis();
    Supplier supplier = supplierService.findSupplierById("5bf18909d9056a1fc43d17e9");
    long end = System.currentTimeMillis();
    System.out.println("For first time took : " + ((end - start) / 1000 + " sec."));
    Assert.assertNotNull(supplier);

    start = System.currentTimeMillis();
    supplier = supplierService.findSupplierById("5bf18909d9056a1fc43d17e9");
    Assert.assertNotNull(supplier);
    end = System.currentTimeMillis();
    System.out.println("Second time took : " + ((end - start) / 1000 + " sec."));

    start = System.currentTimeMillis();
    supplier = supplierService.findSupplierById("5bf18909d9056a1fc43d17e9");
    Assert.assertNotNull(supplier);
    end = System.currentTimeMillis();
    System.out.println("Third time took : " + ((end - start) / 1000 + " sec."));
  }
}
