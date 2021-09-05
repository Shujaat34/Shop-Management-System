package com.bukhari.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

public interface StockDAO {

	public ResultSet getDataResultSet() ;
	public List<String> getProductCodes();
	
	public String[] getBrandCateogryByProductCode(String product_code);
	
	public Integer addStock(String product_code, Timestamp date, String transaction, String price  
			, String store_quantity , String warehousequantity , String salesInvoice);
	public Integer deleteStock(Integer id);
	
	public Integer updateStock(String product_code, Timestamp date, String transaction, String price  
			, String store_quantity , String warehousequantity , String salesInvoice, Integer id) ;

	public Integer addTransferredStock(String stock_id, String store_name, String quantity);
	
	public Integer updateStoreQuantity(String store_quantity,Integer id);
	
	public Integer updateStockQuantities(int store_quantity,int warehouse_quantity, Integer id);
	
	public Integer updateStockQuantitiesByProductCode(int store_quantity,int warehouse_quantity, String product_code);
	
	public Integer getStockStoreQuantity(String product_code);
	public Integer getStockWarehouseQuantity(String product_code);
	

	
}
