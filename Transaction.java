/******************************************************************************
*	Program Author: Kavita Mishra for CSCI 6810 Java and the Internet	  *
*	Date: September, 2018													      *
*******************************************************************************/
package com.mishra;

import java.lang.*; //including Java packages used by this program
import java.sql.*;
import java.util.Random;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.*;
import com.mishra.*;

public class Transaction
{   //Instance Variables
	private String TransactionNumber, TransactionType, TransactionTime, TransactionDate, FromAccount, ToAccount, CustomerID;
	private float Amount = -1;
	private String Startdate, Enddate;
	private Vector TransStore = new Vector();



	public Transaction(String AccountType, String Cust_ID, String Amt){
		ToAccount = AccountType;
		CustomerID = Cust_ID;
		Amount = Float.parseFloat(Amt);
}

	public Transaction(String ToAcc, String FromAcc, String Cust_ID, String Amt, String Trans_type) { //Constructor One with four parameters
		ToAccount = ToAcc;
		FromAccount = FromAcc;
		CustomerID = Cust_ID;
		TransactionType = Trans_type;
		Amount = Float.parseFloat(Amt);
	}
	public Transaction(String SD, String ED){

			Startdate = SD;
			Enddate = ED;
		}


	public String recordTransaction() {
			     boolean done = true;
						try {
						    if (done) {
						        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
						        Connection DBConn = ToDB.openConn();
						        Statement Stmt = DBConn.createStatement();
						        String SQL_Command;
						        while(done) {
									Random rand = new Random();
									int n = rand.nextInt(9999) + 1000;
									TransactionNumber = Integer.toString(n);
						        	SQL_Command = "SELECT TransactionNumber FROM Transactions WHERE TransactionNumber ='"+TransactionNumber+"'"; //SQL query command
						        	ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username exsits.
						        	done = Rslt.next();
								}
						        if (!done) {
									LocalTime nowTime = java.time.LocalTime.now();
									LocalDate nowDate = java.time.LocalDate.now();
									DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
									DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
									TransactionTime = nowTime.format(formatterTime);
									TransactionDate = nowDate.format(formatterDate);
									//TransactionType = "Deposit";
								    SQL_Command = "INSERT INTO Transactions(TransactionNumber, TransactionType, TransactionAmount, TransactionTime, TransactionDate, FromAccount, ToAccount, CustomerID)"+
								                  " VALUES ('"+TransactionNumber+"','"+TransactionType+"','"+Amount+"','"+TransactionTime+"', '"+TransactionDate+"', '"+FromAccount+"', '"+ToAccount+"', '"+CustomerID+"')"; //Save the username, password and Name
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
			    return TransactionNumber;
		}
		public Vector searchTransaction(String UName){
						boolean done;

						try {
							done = !Startdate.equals("") && !Enddate.equals("");
					        DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					        Connection DBConn = ToDB.openConn();
					        Statement Stmt = DBConn.createStatement();
					        String SQL_Command = "SELECT * FROM Transactions WHERE TransactionDate BETWEEN '"+Startdate+ "' AND '"+Enddate+ "'"; //SQL query command
					        ResultSet Rslt = Stmt.executeQuery(SQL_Command);
					        while (Rslt.next()) {
								Vector Column_Names = new Vector();

									String TransNumber = Rslt.getString("TransactionNumber");
									String TransAmount = Rslt.getString("TransactionAmount");
									String TransType = Rslt.getString("TransactionType");
									String TransTime = Rslt.getString("TransactionTime");
									String TransDate = Rslt.getString("TransactionDate");
									String FromAcc = Rslt.getString("FromAccount");
									String ToAcc = Rslt.getString("ToAccount");
									Column_Names.add(0, TransNumber);
									Column_Names.add(1, TransAmount);
									Column_Names.add(2, TransType);
									Column_Names.add(3, TransTime);
									Column_Names.add(4, TransDate);
									Column_Names.add(5, FromAcc);
									Column_Names.add(6, ToAcc);
									TransStore.add(Column_Names);
							}
						}
					    catch(SQLException e)
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
					    catch (Exception e)
					    {
								 System.out.println("Exception: " + e);
								 e.printStackTrace ();
					    }
					    return TransStore;
				}


}