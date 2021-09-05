package com.bukhari.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;

public interface LoanDAO {
	public ResultSet getDataResultSet();//Integer custoemr_id
	public Integer deleteLoan(Integer id);
	public Integer updateLoanAmount(String rem_amount ,  int id);
	public Integer addIntoLoanPaid(String payment_type, String amount_paid, String chequeNum, Timestamp date_paid,
			int payment_id);
	public ResultSet getDataResultSetTransaction(int payment_id);
}
