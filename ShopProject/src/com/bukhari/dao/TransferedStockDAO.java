package com.bukhari.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface TransferedStockDAO {
	
	public ResultSet getDataResultSet();
	public Integer deleteTransferedStock(Integer id);
	public Integer updateTransferedStock(String store_name, String quantity, Integer id);
	
}
