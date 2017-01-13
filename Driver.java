package com.atm.main;


import java.sql.*;

public class Driver {
	
	private Connection myConn = null;
	private Statement myStmt = null;
	private ResultSet myRs = null;
	
	private String dbURL = "jdbc:mysql://localhost:3306/atm?useSSL=false";
	private String user = "*******";
	private String password = "******";
	
	private User driverUser;
	
	public Driver() throws SQLException
	{
		try {

			this.myConn = DriverManager.getConnection(this.dbURL,this.user,this.password);
			
			System.out.println("\nDatabase connection successful!\n");

			this.myStmt = this.myConn.createStatement();

			this.myRs = this.myStmt.executeQuery("select * from user");

		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (this.myRs != null) {
				this.myRs.close();
			}
			
			if (this.myStmt != null) {
				this.myStmt.close();
			}
			
			if (this.myConn != null) {
				this.myConn.close();
			}
		}
	}
	
	public boolean checkLogin(String name, String password) throws SQLException
	{
		this.myConn = DriverManager.getConnection(this.dbURL,this.user,this.password);		
		this.myStmt = this.myConn.createStatement();
		this.myRs = this.myStmt.executeQuery("select * from user");
		boolean q = false;
		while(myRs.next())
		{
			if(this.myRs.getString("name").equals(name) && this.myRs.getString("password").equals(password))
			{
				System.out.println("Login succesfull!");
				this.driverUser = new User(this.myRs.getString("name"),this.myRs.getString("password"), this.myRs.getInt("account"),
						this.myRs.getInt("idUser"));
				q = true;
				return q;
			}
		}
		System.out.println("Wrong username or password!");
		this.myRs.close();
		this.myStmt.close();
		this.myConn.close();
		return q;
	}
	
	public User getDriverUser() {
		return driverUser;
	}

	public void setDriverUser(User driverUser) {
		this.driverUser = driverUser;
	}

	public void updateUser(User user)throws SQLException
	{
		this.myConn = DriverManager.getConnection(this.dbURL,this.user,this.password);		
		this.myStmt = this.myConn.createStatement();
		
		String sql = "update user set account = " + user.getAccount() + " where idUser = " + user.getId();
		myStmt.executeUpdate(sql);
		this.myRs.close();
		this.myStmt.close();
		this.myConn.close();
	}
	
	public boolean insertUser(User user)throws SQLException
	{
		int maxId = -1;
		this.myConn = DriverManager.getConnection(this.dbURL,this.user,this.password);		
		this.myStmt = this.myConn.createStatement();
		this.myRs = this.myStmt.executeQuery("select name,idUser from user");
		while(myRs.next())
		{
			if(myRs.getString("name").equals(user.getName()))
			{
				System.out.println("This username is already taken!");
				return false;
			}
			if(maxId < myRs.getInt("idUser"))
				maxId = myRs.getInt("idUser");
		}		
		maxId++;
		String sql = "insert into user" 
					+ "(idUser, name, password, account)" +
					"values (' " + maxId + "', '" + user.getName() + "','" + user.getPassword() + "','0')";
		myStmt.executeUpdate(sql);
		
		this.myRs.close();
		this.myStmt.close();
		this.myConn.close();
		return true;
	}

}
