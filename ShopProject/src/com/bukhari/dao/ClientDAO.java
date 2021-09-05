package com.bukhari.dao;

import java.sql.ResultSet;

public interface ClientDAO {
	public ResultSet getDataResultSet();
	
	public Integer addClient(String firstName,String middleName,String lastName,String address,String contact,String bank,String company);
	
	public Integer deleteClient(Integer id);
	
	public Integer updateClient(String firstName, String middleName, String lastName, String address, String contact,
			String bank, String company,Integer id);
	public boolean clientIdExist(String id);
	
	public Integer addPayment(String payment_type, String customer_id, String price_per_product, String product_id,Integer quantity,String dateapproved,Integer duration,String pay_num,Float total);
	
}
