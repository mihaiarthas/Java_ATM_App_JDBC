package com.atm.main;

import java.sql.SQLException;
import java.util.Scanner;

public class Utility {
	
	Scanner scan = new Scanner(System.in);
	private String consoleUser;
	private String consolePass;
	private User currentUser;
	
	public void Login() throws SQLException
	{
		boolean running = true;
		
		while(running)
		{
			String userInput;
			Driver driver = new Driver();
			System.out.println("      Hello !");
			System.out.println("   1. Login");
			System.out.println("   2. Register");
			System.out.println("   3. Exit app");
		
			userInput = scan.next();
		
			switch(userInput)
			{
			case "1":
				System.out.println("   ### LOGIN ###\n\n");
				System.out.println(" Please enter your username and password!");
				System.out.print(" User : ");
				consoleUser = scan.next();
				System.out.print(" Password : ");
				consolePass = scan.next();

		
				if(driver.checkLogin(consoleUser, consolePass) == true)
				{
					this.currentUser = driver.getDriverUser();
					boolean loginRunning = true;
					
					
					while(loginRunning)
					{
						System.out.println("   1. Deposit");
						System.out.println("   2. Withdraw");
						System.out.println("   3. Back");
						System.out.println("   4. Exit app\n\n");
						System.out.println("\n      Current Amount : " + currentUser.getAccount() + "$" );
						
						userInput = scan.next();
						
						switch(userInput)
						{
						case"1":
							int amount;
							System.out.print("Amount to deposit :");
							amount = scan.nextInt();
							currentUser.deposit(amount);
							driver.updateUser(currentUser);
							break;
							
						case "2":
							int amount2;
							System.out.print("Amount to withdraw :");
							amount2 = scan.nextInt();
							currentUser.withdraw(amount2);
							driver.updateUser(currentUser);
							break;
							
						case "3":
							loginRunning = false;
							break;
						case "4":
							
							System.out.println(" App terminated!");
							loginRunning = false;
							running = false;
							break;
							
						}
					}
				}
				
				
				break;
			
			
			case "2":
				String registerUser;
				String registerPass1;
				String registerPass2;
				System.out.println("   ### REGISTER ###\n\n");
				System.out.print(" Type username : ");
				registerUser = scan.next();
				System.out.print(" Type password : ");
				registerPass1 = scan.next();
				System.out.print(" Type password again : ");
				registerPass2 = scan.next();
				
				if(registerPass1.equals(registerPass2))
				{
					currentUser = new User(registerUser, registerPass1);
					if(driver.insertUser(currentUser) == true)
						System.out.println("Please login, press 1 to continue!");
					else System.out.println("Press 1 to try again!");
					scan.next();
				}
				else
				{
					System.out.println("\nThe passwords don`t match, please try again!\n");
				}
				break;
				
				
			case "3":
				System.out.println(" App terminated!");
				running = false;
				break;
			default:
				running = false;
			}
		}
	}
}
