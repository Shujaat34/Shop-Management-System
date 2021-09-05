package com.bukhari.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bukhari.connection.DBConnection;
import com.bukhari.dao.ClientDAO;

public class ClientDAOImpl implements ClientDAO {
	Connection con = DBConnection.getConnection();
	@Override
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM client;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	@Override
	public Integer addClient(String firstName, String middleName, String lastName, String address, String contact,
			String bank, String company) {
		
		String sql = "INSERT INTO client(first_name,middle_name,last_name,address,contactNo,bank,company) VALUES (?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, middleName);
			ps.setString(3, lastName);
			ps.setString(4, address);
			ps.setString(5, contact);
			ps.setString(6, bank);
			ps.setString(7, company);
	
			
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	@Override
	public Integer deleteClient(Integer id) {
		PreparedStatement ps = null;
		String sql ="DELETE FROM client where id=?";
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
	public Integer updateClient(String firstName, String middleName, String lastName, String address, String contact,
			String bank, String company,Integer id){

		String sql = "UPDATE client SET first_name=?,middle_name=?,last_name=?,address=?,contactNo=?,bank=?,company=? WHERE id=?";
		
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, middleName);
			ps.setString(3, lastName);
			ps.setString(4, address);
			ps.setString(5, contact);
			ps.setString(6, bank);
			ps.setString(7, company);
			
			ps.setInt(8, id);
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
		
	}
	@Override
	public boolean clientIdExist(String id){
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM client where id=?;";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}


	@Override
	public Integer addPayment(String payment_type, String customer_id, String price_per_product, String product_id,Integer quantity,String dateapproved,Integer duration,String pay_num,Float total) {
		String sql = "INSERT INTO payment(payment_type,customer_id,price_per_product,product_id,quantity,date_approved,duration,pay_num,total) VALUES (?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, payment_type);
			ps.setString(2, customer_id);
			ps.setString(3, price_per_product);
			ps.setString(4, product_id);
			ps.setInt(5, quantity);
			ps.setString(6, dateapproved);
			ps.setInt(7, duration);
			ps.setString(8, pay_num);
			ps.setFloat(9, total);
			
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
}
