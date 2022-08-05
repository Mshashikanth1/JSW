package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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
 * Servlet implementation class bal
 */

public class bal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter();
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","Mshashikanth@1");
			//data c= new data();
			//System.out.println(c.username);
			Cookie[] ck= request.getCookies();
			String username= ck[0].getValue();
			Statement s=conn.createStatement();
			System.out.println(conn);
			ResultSet r= s.executeQuery("select * from data where username="+"\""+username+"\"");
			
			r.next();
			int bal=r.getInt(3);
		    
			out.println(bal +"  is the final amount you have in your account");
			conn.close();
			out.println("<head>\r\n"
					+ "<meta charset=\"ISO-8859-1\">\r\n"
					+ "<title>JSW</title>\r\n"
					+ "<style>\r\n"
					+ "         body {\r\n"
					+ "            background-image: url(\"jsw.png\");\r\n"
					+ "         }\r\n"
					+ "      </style>\r\n"
					+ "</head>"+"<body>\r\n"
				    +"<br>"
					+ "     <input type=\"button\" value=\"back to menu\" onclick=\"history.back()\"><br>\r\n"
				
					+ "    </form>\r\n"
					+ "</body>");
			
		}catch(Exception e) {out.println(e+"\n session expired or login again");}
	}
	

}
