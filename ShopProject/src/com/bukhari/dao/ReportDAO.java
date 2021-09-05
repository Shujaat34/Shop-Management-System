package com.bukhari.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;

public interface ReportDAO {
	public ResultSet getDataResultSet(String payment_type)  ;
	
	public ResultSet getDataResultSetForLastReport();
	
	public ResultSet getDataResultsetByDate(Timestamp time);
	
}
