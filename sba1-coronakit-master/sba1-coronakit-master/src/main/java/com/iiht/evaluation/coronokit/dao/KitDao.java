package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;



public class KitDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public KitDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public boolean insertCustomerOrderDetails(CoronaKit coronaKit, List<KitDetail> listProducts) throws SQLException, ClassNotFoundException 
	{
		
		this.connect();
		PreparedStatement stmt=null;;
		int recordsAdded=0;
		
		for(int i=0;i<listProducts.size();i++)
		{
		stmt=jdbcConnection.prepareStatement("insert into customerorderdetails(CoronaKitid,CustName,CustEmail,CustPhone,ProductId,ProductName,ProductQuantity,ProductPurchaseAmt,CustDeliveryAddress ) values(?,?,?,?,?,?,?,?,?)");
		
		stmt.setInt(1, coronaKit.getId());
		stmt.setString(2, coronaKit.getPersonName());
		stmt.setString(3, coronaKit.getEmail());
		stmt.setLong(4, Long.parseLong(coronaKit.getContactNumber()));
		stmt.setInt(5, listProducts.get(i).getProduct().getId());
		stmt.setString(6, listProducts.get(i).getProduct().getProductName());
		stmt.setInt(7, listProducts.get(i).getQuantity());
		stmt.setInt(8, listProducts.get(i).getAmount());
		stmt.setString(9, coronaKit.getDeliveryAddress());
		recordsAdded+=stmt.executeUpdate();
	}
		
		stmt.close();
		this.disconnect();
		
		if(recordsAdded>0)
			return true;
		else 
			return false;
		
	
		
}

	
}