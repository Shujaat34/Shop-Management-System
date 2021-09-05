package com.bukhari.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.bukhari.connection.DBConnection;
import com.bukhari.dao.LoanDAO;

public class LoanDAOImpl implements LoanDAO{
	
	Connection con = DBConnection.getConnection();
	@Override
	public ResultSet getDataResultSet() {//Integer custoemr_id
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT p.id,p.customer_id,cl.first_name,pr.product_code,p.date_approved,p.payment_type,p.duration,p.total FROM payment p INNER JOIN product pr ON "
				+ "pr.id=p.product_id INNER JOIN CLIENT cl ON cl.id=p.customer_id WHERE p.payment_type=?";// and p.customer_id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "Loan");
//			ps.setInt(2, custoemr_id);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
//	
	
	@Override
	public Integer deleteLoan(Integer id) {
		PreparedStatement ps = null;
		String sql ="DELETE FROM payment where id=?";
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
	public Integer updateLoanAmount(String rem_amount ,  int id){
		String sql = "UPDATE payment SET total=? where id =?";
		
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, rem_amount);
			ps.setInt(2, id);
			
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
//INSERT INTO loan_paid(payment_type,amount_paid,chequeNum,date_paid,payment_id) VALUES (?,?,?,?,?)
	@Override
	public Integer addIntoLoanPaid(String payment_type, String amount_paid, String chequeNum, Timestamp date_paid,
			int payment_id) {
		String sql = "INSERT INTO loan_paid(payment_type,amount_paid,chequeNum,date_paid,payment_id) VALUES (?,?,?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, payment_type);
			ps.setString(2, amount_paid);
			ps.setString(3, chequeNum);
			ps.setTimestamp(4, date_paid);
			ps.setInt(5, payment_id);


			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public ResultSet getDataResultSetTransaction(int payment_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT lp.id,cl.first_name,lp.payment_Type,lp.amount_paid,lp.date_paid , lp.balance FROM loan_paid lp INNER JOIN payment p "
				+ "ON lp.payment_id=p.id INNER JOIN CLIENT cl ON p.customer_id=cl.id WHERE lp.payment_id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, payment_id);
//			ps.setInt(2, custoemr_id);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
