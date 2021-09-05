package com.bukhari.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.bukhari.connection.DBConnection;
import com.bukhari.dao.ReportDAO;

public class ReportDAOImpl implements ReportDAO{
	Connection con = DBConnection.getConnection();
	@Override
	public ResultSet getDataResultSet(String payment_type) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT p.id,p.payment_type,p.pay_num,pr.brand,pr.category,pr.price,p.quantity,c.first_name,c.last_name,c.address,c.contactno,c.bank FROM payment p "
				+ "INNER JOIN client c on p.customer_id=c.id "
				+ "INNER JOIN product pr on pr.id=p.product_id where p.payment_type=?;";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, payment_type);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	@Override
	public ResultSet getDataResultSetForLastReport() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT st.arrival_date,st.product_code,st.sales_invoiceNo,pr.description,p.total , p.payment_type FROM stock st INNER JOIN product pr on pr.product_code = st.product_code INNER JOIN payment p ON "
				+"p.product_id = pr.id GROUP BY pr.product_code;";
		try {
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	@Override	
	public ResultSet getDataResultsetByDate(Timestamp time) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT st.arrival_date,st.product_code,st.sales_invoiceNo,pr.description,pr.price , p.payment_type FROM stock st INNER JOIN product pr on pr.product_code = st.product_code INNER JOIN payment p ON "
				+"p.product_id = pr.id GROUP BY pr.product_code having st.arrival_date>=?;";
		try {
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1, time);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
