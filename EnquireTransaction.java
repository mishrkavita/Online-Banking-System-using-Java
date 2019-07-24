package com.mishra;

import java.util.*;
import java.sql.*;
import com.mishra.*;

public class EnquireTransaction {
	private String UName, StartDate, EndDate;
	private Vector C_Names, Rows;
	public Vector[] FetchTransaction(String Uname,String StartDate,String EndDate) {
		boolean done = !StartDate.equals("") && !EndDate.equals("");
		try {
			Vector C_Names = new Vector();
			Vector Rows = new Vector();
			if (done)
			{
			  C_Names.addElement("Trans_Number");
			  C_Names.addElement("Trans_Amount");
			  C_Names.addElement("Trans_Time");
			  C_Names.addElement("Trans_Date");
			  C_Names.addElement("From_Acc");
			  C_Names.addElement("To_Acc");
			  C_Names.addElement("Trans_Type");

			  DBConnection ToDB = new DBConnection();
			  Connection DBConn = ToDB.openConn();
			  Statement stmt = DBConn.createStatement();
			  String SQL_Command = " SELECT * FROM Transactions WHERE CustomerID = '"+UName+"' AND TransactionDate BETWEEN '"+StartDate+"' AND '"+EndDate+"'";
	          ResultSet rslt = stmt.executeQuery(SQL_Command);
	          ResultSetMetaData rsmd = rslt.getMetaData();
	          while(rslt.next())
	          {
				  DBConnection DC = new DBConnection();
				  Rows.addElement(DC.getNextRow(rslt,rsmd));
				  int CN = rsmd.getColumnCount();
				  for (int i = 0; i<=CN; i++)
				  {
					  C_Names.addElement(rsmd.getColumnName(CN));
				  }
		  	  }
		  	  stmt.close();
		   	  ToDB.closeConn();
			}
		}

	 	 	catch(java.sql.SQLException e)
		 	{done = false;
		 	 System.out.println("SQLException: " + e);
		 	 while (e != null)
		 	 {System.out.println("SQLState: " + e.getSQLState());
		 		System.out.println("Message: " + e.getMessage());
		 		System.out.println("Vendor: " + e.getErrorCode());
		 		e = e.getNextException();
		 		System.out.println("");
		 	 }
		   	}
		 	catch (java.lang.Exception e)
		 	{done = false;
		 	 System.out.println("Exception: " + e);
		 	 e.printStackTrace ();
			}
		Vector[] TransInfo = new Vector[2];
		TransInfo[0] = C_Names;
		TransInfo[1] = Rows;
		return TransInfo;
}
}



