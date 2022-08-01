package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class exit
 */
public class exit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter();
		out.println("<head>\r\n"
				+ "<meta charset=\"ISO-8859-1\">\r\n"
				+ "<title>JSW</title>\r\n"
				+ "<style>\r\n"
				+ "         body {\r\n"
				+ "            background-image: url(\"jsw.png\");\r\n"
				+ "         }\r\n"
				+ "      </style>\r\n"
				+ "</head>"+"<body>\r\n"
				+ "Welcome to Jsw ATM<br>\r\n"
				+ "<form action=\"./perform\" method=\"post\">\r\n"
				+ "User Name:<input type=\"text\" name=\"username\"><br>\r\n"
				+ "Password :<input type=\"text\" name=\"password\"><br>\r\n"
				+ "<input type=\"submit\" value=\"Login\"><br>\r\n"
				+ "</form>\r\n"
				+ "</body>");
	}

}
