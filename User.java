package com.atm.main;

public class User {
	
	private int idUser;
	private String name;
	private String password;
	private int account;
	
	public User(String name, String password, int account, int id)
	{
		this.idUser = id;
		this.name = name;
		this.password = password;
		this.account = account;
	}
	
	public User(String name, String password)
	{
		this.name = name;
		this.password = password;
	}
	
	public User(){}
	
	public void deposit(int amount)
	{
		this.account += amount;
	}
	
	public void withdraw(int amount)
	{
		if(amount <= this.account)
			this.account -= amount;
		else
			System.out.println("Not enough funds!");
	}
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public int getId() {
		return idUser;
	}

	public void setId(int id) {
		this.idUser = id;
	}
	
	
	

}
