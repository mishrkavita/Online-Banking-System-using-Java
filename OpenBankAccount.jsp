<!--
/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/
-->

<HTML><HEAD></HEAD>
<BODY>
    <FORM NAME="OpenBankAccountForm" ACTION="/CSCI6810/OpenBankAccount.jsp" METHOD ="POST">
        <INPUT TYPE='hidden' NAME='UserID' VALUE='<%=request.getParameter("UserID")%>'>

        <TABLE cellPadding=3 ALIGN='center'>

            <TR bgcolor='#ECFAEB'>
                <TD>Open Bank Account:</TD>
                <TD>
                    <select size="1" name="CheckingOrSavings">
		    	<option selected value="Checking">Checking</option>
		    	<option value="Savings">Savings</option>    
  	            </select>
                </TD>
            </TR>

            <TR bgcolor='#F1F1FD'>
                <TD>User Name:</TD>
                <TD>
                   <INPUT TYPE='text' NAME='UserNameField' Value='' SIZE='15'>
                </TD>
            </TR>
            <TR bgcolor='#F1F1FD'>
	        <TD>Customer Name:</TD>
	        <TD>
	           <INPUT TYPE='text' NAME='CustomerNameField' Value='' SIZE='15'>
	        </TD>
            </TR>
            <TR bgcolor='#F1F1FD'>
	    	<TD>Account Number:</TD>
	    	<TD>
	    	   <INPUT TYPE='text' NAME='AccountNumberField' Value='' SIZE='15'>
	    	</TD>
            </TR>
            <TR bgcolor='#F1F1FD'>
	        <TD>Opening Deposit:</TD>
	    	<TD>
	    	   <INPUT TYPE='text' NAME='OpeningDepositField' Value='' SIZE='15'>
	        </TD>
            </TR>
          </TABLE><BR>
<CENTER><INPUT TYPE="SUBMIT" NAME='submitBNTN' VALUE='Open'></CENTER><BR>
</FORM>

</BODY>
</HTML>
<SCRIPT LANGUAGE='JavaScript'>
</SCRIPT>