package com.bukhari.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bukhari.connection.DBConnection;
import com.bukhari.dao.ProductDAO;

public class ProductDAOImpl implements ProductDAO{
	Connection con = DBConnection.getConnection();
	@Override
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM product;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	@Override
	public Integer addProduct(String product_no, String brand, String category, String description 
			, String store_quantity , String warehousequantity , String capital,String price, String supplier,String product_code) {
		String sql = "INSERT INTO product(product_no,brand,category,description,store_quantity,warehouse_quantity,capital,price,supplier,product_code) values(?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, product_no);
			ps.setString(2, brand);
			ps.setString(3, category);
			ps.setString(4, description);
			ps.setString(5, store_quantity);
			ps.setString(6, warehousequantity);
			ps.setString(7, capital);
			ps.setString(8, price);
			ps.setString(9, supplier);
			ps.setString(10, product_code);
			
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public Integer deleteProduct(Integer id) {
		PreparedStatement ps = null;
		String sql ="DELETE FROM product where id=?";
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

	@Override
	public Integer updateProduct(String product_no, String brand, String category, String description,
			String store_quantity, String warehousequantity, String capital, String price, String supplier,
			String product_code,Integer id) {

		String sql = "UPDATE product SET product_no=?,brand=?,category=?,description=?,store_quantity=?,warehouse_quantity=?,capital=?,price=?,supplier=?,product_code=? WHERE id=?";
		
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, product_no);
			ps.setString(2, brand);
			ps.setString(3, category);
			ps.setString(4, description);
			ps.setString(5, store_quantity);
			ps.setString(6, warehousequantity);
			ps.setString(7, capital);
			ps.setString(8, price);
			ps.setString(9, supplier);
			ps.setString(10, product_code);
			
			ps.setInt(11, id);
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
		
	}

	@Override
	public Integer updateProductQuantity(Integer store_quantity,Integer id) {

		String sql = "UPDATE product SET store_quantity=? where id=?;";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, store_quantity);
			ps.setInt(2, id);
		
	
			
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
		
	}
	
	@Override
	public Integer updateStockQuantity(Integer store_quantity,String product_code) {
		String sql = "UPDATE stock SET store_quantity=? where product_code=?;";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, store_quantity);
			ps.setString(2, product_code);
		
	
			
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
		
	}
	
	@Override
	public Integer getStoreQuantityById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int quantity = 0;
		String sql = "SELECT store_quantity FROM product where id=?;";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				quantity = rs.getInt("store_quantity");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantity;
	}
	
	@Override
	public Integer updateProudctQuantities(int store_quantity, int warehouse_quantity, int id) {
		String sql = "UPDATE product SET store_quantity=?,warehouse_quantity=? where id=?;";
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

}
