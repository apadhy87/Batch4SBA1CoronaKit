package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.ProductMaster;



public class ProductMasterDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ProductMasterDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public List<ProductMaster> getProductList() throws SQLException, ClassNotFoundException 
	{
		
		this.connect();
		Statement stmt=jdbcConnection.createStatement();
		List<ProductMaster>  productList= new ArrayList <ProductMaster>();
		
		ResultSet rs = stmt.executeQuery("select * from products");
		while(rs.next())
		{
			ProductMaster product=new ProductMaster(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
			productList.add(product);
			
		}
		
		rs.close();
		stmt.close();
		this.disconnect();
		
		return productList;
				
	}
	public boolean addNewProduct(String name,String description,String price) throws SQLException, ClassNotFoundException 
	{
		
		this.connect();
		PreparedStatement stmt=jdbcConnection.prepareStatement("insert into products(product_name,product_description,product_price) values(?,?,?)");
		stmt.setString(1, name);
		stmt.setString(2, description);
		stmt.setInt(3, Integer.parseInt(price));
		
		int recordsAdded=stmt.executeUpdate();
		
		stmt.close();
		this.disconnect();
		
		if(recordsAdded>0)
		return true;
		
		return false;
				
	}
	
	public boolean updateproductDetails(int productId,String name,String description,String price) throws SQLException, ClassNotFoundException 
	{
		this.connect();
		PreparedStatement stmt=jdbcConnection.prepareStatement("update products set product_name=?,product_description=?,product_price=? where product_id=?");
		
		stmt.setString(1, name);
		stmt.setString(2, description);
		stmt.setInt(3, Integer.parseInt(price));
		stmt.setInt(4, productId);
		
		int recordsAdded=stmt.executeUpdate();
		
		stmt.close();
		this.disconnect();
		
		if(recordsAdded>0)
		return true;
		
		return false;
				
	}
	
	public boolean deleteProduct(int productId) throws SQLException, ClassNotFoundException 
	{
		this.connect();
		PreparedStatement stmt=jdbcConnection.prepareStatement("delete from products where product_id=?");
		
		
		stmt.setInt(1, productId);
		
		int recordsAdded=stmt.executeUpdate();
		
		stmt.close();
		this.disconnect();
		
		if(recordsAdded>0)
		return true;
		
		return false;
				
	}
	public ProductMaster getSpecificProductDetail(int productId) throws SQLException, ClassNotFoundException 
	{
		
		this.connect();
		Statement stmt=jdbcConnection.createStatement();
		ProductMaster product=null;
		ResultSet rs = stmt.executeQuery("select * from products where product_id="+productId);
		while(rs.next())
		{
			 product=new ProductMaster(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
	
			
		}
		
		rs.close();
		stmt.close();
		this.disconnect();
		return product;
				
	}

}