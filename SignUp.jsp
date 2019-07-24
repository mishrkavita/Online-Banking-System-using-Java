<!--
/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet *
*	Date: September, 2012						      *
*******************************************************************************/
-->

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.mishra.*;" %>

<%
	String Username = new String("");
	String Password = new String("");
	String Re_enterPassword = new String("");
	String FullName = new String("");
	
	Username = request.getParameter( "UsernameField" );
	Password = request.getParameter( "PasswordField" );
        Re_enterPassword = request.getParameter( "RePasswordField" );
        FullName = request.getParameter( "NameField" );
              
        Account Acct = new Account(Username, Password, Re_enterPassword, FullName);
%>
<%
	if (!Acct.signUp())
            out.println("Account creation failed because of existing username or invalid username. Please try again!");
        else { 
%>

		<HTML><HEAD></HEAD>
		<BODY bgcolor='#F1F1FD'>
		<h4 ALIGN='center'>Congratulations! You have an account with us. Thank you! You can login now.</h4>
		<FORM NAME="LoginPage" ACTION="/OnlineLoginServlet" METHOD ="POST">
		<TABLE cellPadding='3' ALIGN='center'>
		<TR bgcolor='#ECFAEB'>
		<TD>USERNAME:</TD>
		<TD>
		<INPUT TYPE='text' NAME='UserName' Value='<%= Username %>' SIZE='15' focused>
		</TD>
		</TR>
		<TR bgcolor='#ECFAEB'>
		<TD>PASSWORD:</TD>
		<TD>
		<INPUT TYPE='password' NAME='PassWord' Value='' SIZE='15'>
		<INPUT TYPE='button' NAME='submitBTN' VALUE='Login' onClick="checkInputs()">
		</TD>
		</TR>
		</TABLE>
		</FORM>
		</BODY>
		<SCRIPT LANGUAGE="JavaScript"> 
		function checkInputs()
		{
		var Prompts = "";
		Username = window.document.LoginPage.UserName.value;
		Password = window.document.LoginPage.PassWord.value;
		if (Username == "" || Password == "") {
		if (Username == "")
		Prompts +="Please enter your username!\n";
		if (Password == "")
		Prompts +="Please enter your password!\n";
		if (Prompts != "")
		window.alert(Prompts);
		} else {
		document.LoginPage.submit();
		}
		}
		</SCRIPT>
		</HTML>

<%		}
%>
 
