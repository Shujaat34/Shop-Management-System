package com.bukhari.daoimpl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bukhari.connection.DBConnection;
import com.bukhari.dao.StockDAO;


public class StockDAOImpl implements StockDAO{
	Connection con = DBConnection.getConnection();
	
	@Override
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM stock;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	@Override
	public Integer addStock(String product_code, Timestamp date, String transaction, String price  
			, String store_quantity , String warehousequantity , String salesInvoice) {
		String sql = "INSERT INTO stock(product_code,arrival_date,transaction,price,store_quantity,warehouse_quantity,sales_invoiceNo) values(?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, product_code);
			ps.setTimestamp(2, date);
			ps.setString(3, transaction);
			ps.setString(4, price);
			ps.setString(5, store_quantity);
			ps.setString(6, warehousequantity);
			ps.setString(7, salesInvoice);
	
			
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	@Override
	public String[] getBrandCateogryByProductCode(String product_code) {
		String sql = "SELECT brand,category FROM product where product_code=?";
		PreparedStatement ps = null;
		String brandCategory[] = new String[2];
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, product_code);
	
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				brandCategory[0] = rs.getString("brand");
				brandCategory[1] = rs.getString("category");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return brandCategory;
	}
	
	
//
	@Override
	public Integer deleteStock(Integer id) {
		PreparedStatement ps = null;
		String sql ="DELETE FROM stock where id=?";
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
//
	@Override
	public Integer updateStock(String product_code, Timestamp date, String transaction, String price  
			, String store_quantity , String warehousequantity , String salesInvoice,Integer id) {

		String sql = "UPDATE stock SET product_code=?,arrival_date=?,transaction=?,price=?,store_quantity=?,warehouse_quantity=?,sales_invoiceNo=? where id=?;";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, product_code);
			ps.setTimestamp(2, date);
			ps.setString(3, transaction);
			ps.setString(4, price);
			ps.setString(5, store_quantity);
			ps.setString(6, warehousequantity);
			ps.setString(7, salesInvoice);
			ps.setInt(8, id);
	
			
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
		
	}
	
	@Override
	public List<String> getProductCodes() {
		List<String> teacherList = new ArrayList<>(); 
		try {
		
			String sql="Select product_code From product" ;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				teacherList.add(rs.getString("product_code"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teacherList;

	}
	@Override
	public Integer addTransferredStock(String stock_id, String store_name, String quantity) {
		String sql = "INSERT INTO transferred_stock (stock_id,store_name,quantity) values(?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, stock_id);
			ps.setString(2, store_name);
			ps.setString(3, quantity);
	
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	@Override
	public Integer updateStoreQuantity(String store_quantity,Integer id) {

		String sql = "UPDATE stock SET store_quantity=? where id=?;";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, store_quantity);
			ps.setInt(2, id);
		
	
			
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
		
	}
	
	@Override
	public Integer updateStockQuantities(int store_quantity,int warehouse_quantity, Integer id) {

		String sql = "UPDATE stock SET store_quantity=?,warehouse_quantity=? where id=?;";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, store_quantity);
			ps.setInt(2, warehouse_quantity);
			ps.setInt(3, id);
	
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
		
	}
	@Override
	public Integer updateStockQuantitiesByProductCode(int store_quantity, int warehouse_quantity, String product_code) {
		String sql = "UPDATE stock SET store_quantity=?,warehouse_quantity=? where product_code=?;";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, store_quantity);
			ps.setInt(2, warehouse_quantity);
			ps.setString(3, product_code);
	
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	@Override
	public Integer getStockStoreQuantity(String product_code) {
		String sql = "SELECT store_quantity FROM stock WHERE product_code=?";
		PreparedStatement ps = null;
		Integer quantity =0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, product_code);
	
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				quantity= rs.getInt("store_quantity");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantity;
	}
	
	
	@Override
	public Integer getStockWarehouseQuantity(String product_code) {
		String sql = "SELECT warehouse_quantity FROM stock WHERE product_code=?";
		PreparedStatement ps = null;
		Integer quantity =0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, product_code);
	
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				quantity= rs.getInt("warehouse_quantity");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantity;
	}

	
	
}
