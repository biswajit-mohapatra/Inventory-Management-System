package com.gl.microservices.poc.util;

public class URI {


	public static final String HEARTBEAT = "/heartbeat";
	public static final String CREATE = "/create";
	public static final String UPDATE = "/update";
	
	/*
	 * Supplier mapping details
	 */
	public static final String SUPPLIER = "/supplier";

	public static final String SUPPLIER_FIND_ALL = "/findAllSupplier";

	public static final String SUPPLIER_BY_ID = "/findSupplierById/{supplierId}";

	public static final String SUPPLIER_DELETE_BY_ID = "/deleteSupplierById/{supplierId}";
	
	
	/*
	 * Brand mapping details
	 */
	public static final String BRAND = "/brand";

	public static final String BRAND_FIND_ALL = "/findAllBrand";

	public static final String BRAND_BY_ID = "/findBrandById/{brandId}";

	public static final String BRAND_DELETE_BY_ID = "/deleteBrandById/{brandId}";
	
	/*
	 * Brand supplier mapping details
	 */
	public static final String BRAND_SUP = "/brandSupplier";

	public static final String BRAND_SUP_FIND_ALL = "/findAllBrandSupplier";

	public static final String BRAND_SUP_BY_ID = "/findBrandSupplierById/{brandSupplierId}";

	public static final String BRAND_SUP_DELETE_BY_ID = "/deleteBrandSupplierById/{brandSupplierId}";
	
	public static final String BRAND_SUP_BY_BRAND_NAME = "/findBrandSuppliersByBrandName/{name}";
	
	public static final String BRAND_SUP_BY_SUPPLIER_NAME = "/findBrandSuppliersBySupplierName/{name}";
	
	
	
	/*
	 * Product mapping details
	 */
	public static final String PRODUCT = "/product";

	public static final String PRODUCT_FIND_ALL = "/findAllProduct";

	public static final String PRODUCT_BY_ID = "/findProductbyId/{productId}";

	public static final String PRODUCT_DELETE_BY_ID = "/deleteProductById/{productId}";
	
	
	/*
	 * Product type mapping details
	 */
	public static final String PRODUCT_TYPE = "/productType";

	public static final String PRODUCT_TYPE_FIND_ALL = "/findAllProductType";

	public static final String PRODUCT_TYPE_BY_ID = "/findProductTypeById/{productTypeId}";

	public static final String PRODUCT_TYPE_DELETE_BY_ID = "/deleteProductTypeById/{productTypeId}";
	
	
	/*
	 * Stock mapping details
	 */
	public static final String STOCK = "/stock";

	public static final String STOCK_FIND_ALL = "/findAllStock";

	public static final String STOCK_BY_ID = "/findStockById/{stockId}";

	public static final String STOCK_DELETE_BY_ID = "/deleteStockById/{stockId}";
	
	
	/*
	 * Stock mapping details
	 */
	public static final String STYLE = "/style";

	public static final String STYLE_FIND_ALL = "/findAllStyle";

	public static final String STYLE_BY_ID = "/findStyleById/{styleId}";

	public static final String STYLE_DELETE_BY_ID = "/deleteStyleById/{styleId}";
	
	
	
	

}
