/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: October, 2013													      *
*******************************************************************************/
package com.mishra;

import java.lang.*; //including Java packages used by this program
import java.sql.*;
import com.mishra.*;

public class CheckingAccount
{   //Instance Variables
	private String CheckingAccountNumber, CustomerName, CustomerID;
	private float Balance = -1, Amount = -1;

		public CheckingAccount(String CA_Num, String Cust_Name, String Cust_ID, String Amt) { //Constructor One with three parameters
		CheckingAccountNumber = CA_Num;
		CustomerName = Cust_Name;
		CustomerID = Cust_ID;
		//Balance = Float.parseFloat(Bal);
		Amount = Float.parseFloat(Amt);
	}



	public CheckingAccount(String CA_Num) { //Constructor Two with one parameter
		CheckingAccountNumber = CA_Num;
	}
	public CheckingAccount() { //Constructor with no parameters for fetching the account no.
		}


    public boolean openAcct() {
	     boolean done = false;
				try {
				    if (!done) {
				        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
				        Connection DBConn = ToDB.openConn();
				        Statement Stmt = DBConn.createStatement();
				        String SQL_Command = "SELECT CheckingAccountNumber FROM CheckingAccount WHERE CheckingAccountNumber ='"+CheckingAccountNumber+"'"; //SQL query command
				        ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
				        done = !Rslt.next();
				        if (done) {
						    SQL_Command = "INSERT INTO CheckingAccount(CheckingAccountNumber, CustomerName, Balance, CustomerID)"+
						                  " VALUES ('"+CheckingAccountNumber+"','"+CustomerName+"',"+Amount+", '"+CustomerID+"')"; //Save the username, password and Name
						    Stmt.executeUpdate(SQL_Command);
					    }
					    Stmt.close();
					    ToDB.closeConn();
					}
				}
			    catch(java.sql.SQLException e)
			    {         done = false;
						 System.out.println("SQLException: " + e);
						 while (e != null)
						 {   System.out.println("SQLState: " + e.getSQLState());
							 System.out.println("Message: " + e.getMessage());
							 System.out.println("Vendor: " + e.getErrorCode());
							 e = e.getNextException();
							 System.out.println("");
						 }
			    }
			    catch (java.lang.Exception e)
			    {         done = false;
						 System.out.println("Exception: " + e);
						 e.printStackTrace ();
			    }
	    return done;
	}
	public String getAccno(String C_ID) {  //Method to return a CheckingAccount No.
			try {
			        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
			        Connection DBConn = ToDB.openConn();
			        Statement Stmt = DBConn.createStatement();
			        String SQL_Command = "SELECT CheckingAccountNumber FROM CheckingAccount WHERE CustomerID ='"+C_ID+"'";
			        ResultSet Rslt = Stmt.executeQuery(SQL_Command);

			        while (Rslt.next()) {
					CheckingAccountNumber = Rslt.getString("CheckingAccountNumber");

				    }
				    Stmt.close();
				    ToDB.closeConn();
			}
		    catch(java.sql.SQLException e)
		    {
					 System.out.println("SQLException: " + e);
					 while (e != null)
					 {   System.out.println("SQLState: " + e.getSQLState());
						 System.out.println("Message: " + e.getMessage());
						 System.out.println("Vendor: " + e.getErrorCode());
						 e = e.getNextException();
						 System.out.println("");
					 }
		    }
		    return CheckingAccountNumber;
	}
    public float getBalance() {  //Method to return a CheckingAccount balance
		try {
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT Balance FROM CheckingAccount WHERE CheckingAccountNumber ='"+CheckingAccountNumber+"'"; //SQL query command for Balance
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command);

		        while (Rslt.next()) {
					Balance = Rslt.getFloat(1);
			    }
			    Stmt.close();
			    ToDB.closeConn();
		}
	    catch(java.sql.SQLException e)
	    {
				 System.out.println("SQLException: " + e);
				 while (e != null)
				 {   System.out.println("SQLState: " + e.getSQLState());
					 System.out.println("Message: " + e.getMessage());
					 System.out.println("Vendor: " + e.getErrorCode());
					 e = e.getNextException();
					 System.out.println("");
				 }
	    }
	    catch (java.lang.Exception e)
	    {
				 System.out.println("Exception: " + e);
				 e.printStackTrace ();
	    }
	    return Balance;
	}

    public float getBalance(String ChkAcctNumber) {  //Method to return a CheckingAccount balance
		try {
		        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
		        Connection DBConn = ToDB.openConn();
		        Statement Stmt = DBConn.createStatement();
		        String SQL_Command = "SELECT Balance FROM CheckingAccount WHERE CheckingAccountNumber ='"+ChkAcctNumber+"'"; //SQL query command for Balance
		        ResultSet Rslt = Stmt.executeQuery(SQL_Command);

		        while (Rslt.next()) {
					Balance = Rslt.getFloat(1);
			    }
			    Stmt.close();
			    ToDB.closeConn();
		}
	    catch(java.sql.SQLException e)
	    {
				 System.out.println("SQLException: " + e);
				 while (e != null)
				 {   System.out.println("SQLState: " + e.getSQLState());
					 System.out.println("Message: " + e.getMessage());
					 System.out.println("Vendor: " + e.getErrorCode());
					 e = e.getNextException();
					 System.out.println("");
				 }
	    }
	    catch (java.lang.Exception e)
	    {
				 System.out.println("Exception: " + e);
				 e.printStackTrace ();
	    }
	    return Balance;
	}

	public boolean deposit(String C_ID){

			boolean done= !CheckingAccountNumber.equals("") && !CustomerID.equals("");

			try{
				if(done){
					DBConnection DBconn = new DBConnection();
					Connection conn = DBconn.openConn();
					Statement stat = conn.createStatement();
					String SQL_Command = "SELECT Balance FROM CheckingAccount WHERE CheckingAccountNumber = '"+CheckingAccountNumber+"' AND CustomerID ='"+C_ID+"'";
					System.out.println(SQL_Command);
					ResultSet reslt = stat.executeQuery(SQL_Command);

					while (reslt.next()) {
						 Balance = reslt.getFloat(1);
						}
					Balance = Balance + Amount;
					SQL_Command = "UPDATE CheckingAccount SET Balance = '" + Balance + "' WHERE CheckingAccountNumber = '"+CheckingAccountNumber+"'";
					System.out.println(SQL_Command);
					stat.executeUpdate(SQL_Command);
					stat.close();
					DBconn.closeConn();
					}
				}

			catch (SQLException e){
				System.out.println("SQLException" + e);
				done= false;
				System.out.println("SQLExceptionState" + e.getSQLState());
				System.out.println("message" + e.getMessage());
				System.out.println("vendor" + e.getErrorCode());
				e.getNextException();
				System.out.println("");
			}

			catch (java.lang.Exception e){

				System.out.println("Exception" + e);
				e.printStackTrace();
			}

			return done;
	}
	public boolean Withdraw(String C_ID){

			boolean done = !CheckingAccountNumber.equals("") && !CustomerID.equals("");

			try{
				if(done){
					DBConnection DBconn = new DBConnection();
					Connection Conn = DBconn.openConn();
					Statement Stat = Conn.createStatement();
					String SQL_Command = "SELECT Balance FROM CheckingAccount WHERE CheckingAccountNumber = '"+CheckingAccountNumber+"' AND CustomerID= '"+C_ID+"' ";
					System.out.println(SQL_Command);
					ResultSet rslt = Stat.executeQuery(SQL_Command);
					while (rslt.next()) {
						 Balance = rslt.getFloat(1);
					}
					if (Balance>=Amount) {
						Balance = Balance - Amount;
						SQL_Command = "UPDATE CheckingAccount SET Balance = '"+Balance+"' WHERE  CheckingAccountNumber = '"+CheckingAccountNumber+"'";
						System.out.println(SQL_Command);
						Stat.executeUpdate(SQL_Command);
						Stat.close();
						DBconn.closeConn();

					}
				}
			}
			catch (java.sql.SQLException e){

				System.out.println("SQLException" + e);
				while (e != null){
					System.out.println("SqlExceptionState" + e.getSQLState());
					System.out.println("Message"+ e.getMessage());
					System.out.println("Vendor"+ e.getErrorCode());

					e = e.getNextException();
					System.out.println("");

				}
			}
			catch (java.lang.Exception e){

				System.out.println("Exception" + e);

				e.printStackTrace();

			}
	       return done;
	   }
}