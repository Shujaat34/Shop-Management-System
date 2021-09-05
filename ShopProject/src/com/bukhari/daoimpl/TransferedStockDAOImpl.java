package com.bukhari.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bukhari.connection.DBConnection;
import com.bukhari.dao.TransferedStockDAO;

public class TransferedStockDAOImpl implements TransferedStockDAO{
	
	Connection con = DBConnection.getConnection();
	@Override
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " 	SELECT ts.id,ts.stock_id,ts.store_name,ts.quantity,s.product_code,s.price,s.sales_invoiceNo FROM transferred_stock ts INNER JOIN stock s on s.id = ts.stock_id;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	@Override
	public Integer deleteTransferedStock(Integer id) {
		PreparedStatement ps = null;
		String sql ="DELETE FROM transferred_stock where id=?";
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
	public Integer updateTransferedStock(String store_name, String quantity, Integer id){
		String sql = "UPDATE transferred_stock SET store_name=?,quantity=? WHERE id=?";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, store_name);
			ps.setString(2, quantity);
			ps.setInt(3, id);

			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
		
	}

}
