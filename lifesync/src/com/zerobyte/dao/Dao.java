/*
 * DBDao.java
 * 
 * Created by: Johnathan Chu
 * Modified by: Johnathan Chu
 * 
 * A data access object that allows the application to connect to a MySQL database.
 * 
 * Last Modified: 2012-10-13
 */

package com.zerobyte.dao;

import java.sql.*;

public class Dao {
	String sUrl;
	String sUsername;
	String uPassword;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	/*
	 * Construct an object with a database URL, username, and password
	 */
	Dao( String url, String uname, String pw )
	{
		this.sUrl = url;
		this.sUsername = uname;
		this.uPassword = pw;
	}
	
	/*
	 * Connect to database
	 * 
	 * POST: Return true if connected, return false if exception occurs.
	 */
	public boolean connect()
	{
		try {
			con = DriverManager.getConnection( "jdbc:mysql://" + sUrl, sUsername, uPassword);
		} catch (SQLException e) {
			printExceptionError( e );
		    return false;
		}
		
		return true;
	}
	
	/*
	 * Close connection to database
	 * 
	 * POST: Return true if database resources release and connection close, return false if exception occurs.
	 */
	public boolean close()
	{
		if( rs != null )
		{
			try {
				rs.close();
			} catch (SQLException e) {
				printExceptionError( e );
				return false;
			}
			
			rs = null;
		}
		
		if( stmt != null )
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				printExceptionError( e );
				return false;
			}
			
			stmt = null;
		}
		
		if( con != null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				printExceptionError( e );
				return false;
			}
			
			con = null;
		}
		
		return true;
	}
	
	/*
	 * Prints database exception error in console
	 */
	private void printExceptionError(SQLException e)
	{
		System.out.println("SQLException: " + e.getMessage());
	    System.out.println("SQLState: " + e.getSQLState());
	    System.out.println("VendorError: " + e.getErrorCode());
	    System.out.println();
	    System.out.println();
	}
}
