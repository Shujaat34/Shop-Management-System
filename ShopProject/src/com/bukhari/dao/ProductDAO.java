package com.bukhari.dao;

import java.sql.ResultSet;

public interface ProductDAO {
	public ResultSet getDataResultSet();
	public Integer addProduct(String product_no, String brand, String category, String description 
			, String store_quantity , String warehousequantity , String capital,String price, String supplier,String product_code);
	
	public Integer updateProduct(String product_no, String brand, String category, String description 
			, String store_quantity , String warehousequantity , String capital,String price, String supplier
			,String product_code,Integer id);
	
	public Integer deleteProduct(Integer id);
	
	public Integer updateProductQuantity(Integer store_quantity,Integer id);
	
	public Integer getStoreQuantityById(Integer id);
	
	public Integer updateStockQuantity(Integer store_quantity,String product_code);
	public Integer updateProudctQuantities(int store_quantity, int warehouse_quantity, int id) ;
}
