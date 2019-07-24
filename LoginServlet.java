/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/

import java.io.*;
import javax.servlet.*;  //package for GenericServlet
import javax.servlet.http.*;  //package for HttpServlet
import java.util.*;
import com.mishra.*;

public class LoginServlet extends HttpServlet {
   private String Username, Password;
   private PrintWriter output;

   //a method called automatically to initialize the servlet
   public void init( ServletConfig config ) throws ServletException
   {
      super.init( config );
      Username = new String("");
      Password = new String("");
   }

   //a method included in class HttpServlet to respond
   //to post requests from a client.
   public void doGet ( HttpServletRequest req, HttpServletResponse res )
      throws ServletException, IOException
   {	doPost(req, res);
   }

   //a method included in class HttpServlet to respond
   //to post requests from a client.
   public void doPost ( HttpServletRequest req, HttpServletResponse res )
      throws ServletException, IOException
   {
      //obtains a character-based output stream that enables
      //text data to be sent to the client
      output = res.getWriter();

      //specifies the MIME type of the response to the browser
      res.setContentType( "text/html" );

      //returns the value associated with a parameter sent to
      //the servlet as part of a post request
      Username = req.getParameter( "UserName" );
      Password = req.getParameter( "PassWord" );
      Account Acct = new Account(Username, Password);
      String CustomerName = Acct.signIn();
      if (!CustomerName.equals("")){
		  System.out.println("login username=" + Username);
          //showSuccess();
          req.setAttribute("Username", Username);
		  req.setAttribute("CustomerName", CustomerName);
		  req.getRequestDispatcher("/CSCI6810/afterlogin.jsp").forward(req, res);
      }else
           output.println("login failed");
   }


   //this "cleanup" method is called when a servlet is terminated by the server
   public void destroy() {
       output.close();
   }
}