package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class wwithdraw
 */
import java.sql.*;

import dbcon.database;
public class wwithdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    Connection conn=null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter();
		try{
			Connection conn=  database.connect();
			data c= new data();
			System.out.println(c.username);
			Statement s=conn.createStatement();
			System.out.println(conn);
			ResultSet r= s.executeQuery("select * from data where username="+"\""+c.username+"\"");
			
			r.next();
			int bal=r.getInt(3);
			if(bal<Integer.parseInt(request.getParameter("bal"))) {out.println("Insufficient Funds")
				
				;}
			else {
			int n= bal-Integer.parseInt(request.getParameter("bal"));
			s.executeUpdate("update data set bal="+n+"  where username=\""+c.username+"\";");
			conn.close();
			out.println(request.getParameter("bal")+" money is  with drawn");
			}
			
		
		out.println("<head>\r\n"
				+ "<meta charset=\"ISO-8859-1\">\r\n"
				+ "<title>JSW</title>\r\n"
				+ "<style>\r\n"
				+ "         body {\r\n"
				+ "            background-image: url(\"jsw.png\");\r\n"
				+ "         }\r\n"
				+ "      </style>\r\n"
				+ "</head>"+"<body>\r\n"
				+ "<form >\r\n"
				+ "     <input type=\"button\" value=\"back to menu\" onclick=\"history.back()\"><br>\r\n"
				+ "    </form>\r\n"
				+ "</body>");
		}catch(Exception e) {out.println(e);}
	}

}
