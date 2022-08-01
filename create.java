package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class create
 */
public class create extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String salt="";
	String encrypt(String enp) {
		System.out.println("performing encryption");
		return enp+salt;
		
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useSSL=false","root","Mshashikanth@1");
			
			//Connection conn= new database().connect();
			System.out.println(conn);
			Statement s=conn.createStatement();
			System.out.println(conn);
			s.executeUpdate("insert  into data (username, password) values (\""+request.getParameter("username")+"\" , \""+encrypt(request.getParameter("password"))+"\");");
			//conn.commit();
			conn.close();
			out.println("<head>\r\n"
					+ "<meta charset=\"ISO-8859-1\">\r\n"
					+ "<title>JSW</title>\r\n"
					+ "<style>\r\n"
					+ "         body {\r\n"
					+ "            background-image: url(\"jsw.png\");\r\n"
					+ "         }\r\n"
					+ "      </style>\r\n"
					+ "</head>"+"User is created added");
			}catch(Exception e) {out.println(e.getMessage());}
		
	}

}
