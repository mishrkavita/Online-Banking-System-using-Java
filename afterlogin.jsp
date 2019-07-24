<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.mishra.*;" %>

<%
	String Username = new String("");
	Username = String.valueOf(request.getAttribute("Username"));
	//out.println("Your username is " + Username);
	
	String Name = new String("");
	Name = String.valueOf(request.getAttribute("CustomerName"));
	//out.println("Your customername is " + Name);
%>

<HTML>
<HEAD>
<STYLE> 
	ul{list-style-type: none; padding: 2%;} 
	li{display: inline; padding: 2%;}
</STYLE>
</HEAD>
<BODY>
<h4 ALIGN='center'>Welcome to your link page</h4>
<FORM NAME=\"LoginPage\" ACTION=\"/OnlineLoginServlet\" METHOD =\"POST\">
<TR bgcolor='#ECFAEB'>
<div id='navigation'>
<ul>
<li><A HREF='/CSCI6810/AccountOverview.jsp?UserID=<%=Username%>&CustomerName=<%=Name%>' TARGET='display'>Account Overview</A></li>
<li><A HREF='/CSCI6810/PreOpenBankAccount.jsp?UserID=<%=Username%>&CustomerName=<%=Name%>' TARGET='display'>Open Bank Account</A></li>
<li><A HREF='/CSCI6810/PreWithdraw.jsp?UserID=<%=Username%>&CustomerName=<%=Name%>' TARGET='display'>Withdraw</A></li>
<li><A HREF='/CSCI6810/PreDeposit.jsp?UserID=<%=Username%>&CustomerName=<%=Name%>' TARGET='display'>Deposit</A></li>
<li><A HREF='/CSCI6810/PreTransfer.jsp?UserID=<%=Username%>&CustomerName=<%=Name%>' TARGET='display'>Transfer</A></li>
<li><A HREF='/CSCI6810/InquireTransactions.jsp?UserID=<%=Username%>&CustomerName=<%=Name%>' TARGET='display'>Inquire Transaction</A></li>
<li><A HREF='/CSCI6810/' TARGET='display'>Logout</A></li>
</ul>
</div>
</TR>
</FORM>
</BODY>
<SCRIPT LANGUAGE=\"JavaScript\">
function checkInputs()
{
	var Prompts = \"\";
	Username = window.document.LoginPage.UserName.value;
	Password = window.document.LoginPage.PassWord.value;
	if (Username == \"\" || Password == \"\") 
	{
		if (Username == "")
		Prompts +=\"Please enter your username!\n";
		if (Password == "")
		Prompts +=\"Please enter your password!\n";
		if (Prompts != "")
		window.alert(Prompts);
	}
	else 
	{	
		document.LoginPage.submit();
	}
}
</SCRIPT>
</HTML>