package servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Calendar;

import dbcon.database;
/**
 * Servlet implementation class perform
 */



public class perform extends HttpServlet {
	private static final long serialVersionUID = 1L;
      Connection conn=null;
      //String enpd=null;
     
      
   String decrypt(String dcp,String salt) {
		   System.out.println("performing decryption");
		   dcp=dcp.replace(salt,"");
		   System.out.println(dcp);
		   return dcp;
	        } 
	  
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pass=(String) request.getParameter("password");
		
		PrintWriter out= response.getWriter();
		try {
			
			Connection conn=  database.connect();
			//Connection conn= new database().connect();
			System.out.println(conn);
			Calendar c = Calendar.getInstance();
	        System.out.println("The Current Date is:" + c.getTime());
			Statement s=conn.createStatement();
			
			
			ResultSet r= s.executeQuery("select * from data where username="+"\""+request.getParameter("username")+"\"");
			
			r.next();
			String salt=r.getString(4);
			System.out.println(salt);
			
			String dpass=decrypt(r.getString(2),salt);
			System.out.println(pass+" ,"+ dpass);
			if(pass.equals(dpass)){
				//data d= new data();
				//d.username=request.getParameter("username");
				Cookie ck =new Cookie("username",request.getParameter("username"));
				response.addCookie(ck);
				String username=request.getParameter("username");
				conn.close();
				//request.setAttribute("username", "shashi");
				out.println("<head>\r\n"
						+ "<meta charset=\"ISO-8859-1\">\r\n"
						+ "<title>JSW</title>\r\n"
						+ "<style>\r\n"
						+ "         body {\r\n"
						+ "            background-image: url(\"jsw.png\");\r\n"
					
                       // +"				font-weight: bold;\r\n"
						+ "         }\r\n"
						+ "      </style>\r\n"
						+ "</head>"+"<img src=\"user.png\"  width=\"128\" height=\"128\"><br>hii "+username+",login sucessful<br>"+"The Current Date is:" + c.getTime()+" <body >\r\n"
						
						+ "<form action=\"./wwithdraw\" method=\"post\" >\r\n"
						+ "Enter The Amount To be With Drawn: <input type=\"numbers\" name=\"bal\" value=><br>"
						+ "<input type=\"Submit\" value=\"withdraw\"><br>\r\n"
						+ "</form>\r\n"
						+ "</body>"
						+ ""
						+ "<body>\r\n"
						
						+ "<form action=\"./deposit\" method=\"post\">\r\n"
						+"Enter The Amount To be With Deposited :<input type=\"numbers\" name=\"bal\" ><br>"
						+ "<input type=\"Submit\" value=\"deposit\"><br>\r\n"
						+ "</form>\r\n"
						+ "</body>"
						+ ""
						+ "<body>\r\n"
						
						+ "<form action=\"./bal\" method=\"post\">\r\n"
						+ "<input type=\"Submit\" value=\"check bal\"><br>\r\n"
						+ "</form>\r\n"
						+ "</body>"
						+ ""
						+ "<body>\r\n"
						
						+ "<form action=\"./exit\" method=\"post\">\r\n"
						+ "<input type=\"Submit\" value=\"exit\"><br>\r\n"
						+ "</form>\r\n"
						+ "</body>"
						
                        + "<form action=\"./statement\" method=\"post\">\r\n"
                        + "<input type=\"Submit\" value=\"Print Statement \"><br>\r\n"
                        + "</form>\r\n"
                        + "</body>"
                      
						);
				
				
				
				
				
			}
			else {
				out.println("<!DOCTYPE html>\r\n"
						+ "<html>\r\n"
						+ "<head>\r\n"
						+ "<meta charset=\"ISO-8859-1\">\r\n"
						+ "<title>JSW</title>\r\n"
						+ "<style>\r\n"
						+ "         body {\r\n"
						+ "            background-image: url(\"jsw.png\");\r\n"
						+ "         }\r\n"
						+ "      </style>\r\n"
						+ "</head>\r\n"
						+ "<body >"+"<body>\r\n"
						+"Enter correct Details (or) create user"
						+ "<form action=\"./create\" method=\"post\">\r\n"
						+ "<input type=\"Submit\" value=\"Create User\"><br>\r\n"
						+ "</form>\r\n"
						+ "</body>");
			}
			System.out.println("you can still execute the queryes");
			
			}catch(Exception e) {out.println("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<head>\r\n"
					+ "<meta charset=\"ISO-8859-1\">\r\n"
					+ "<title>JSW</title>\r\n"
					+ "<style>\r\n"
					+ "         body {\r\n"
					+ "            background-image: url(\"jsw.png\");\r\n"
					+ "         }\r\n"
					+ "      </style>\r\n"
					+ "</head>\r\n"
					+ "<body ><br><br>"+e);}
		
	}

}
