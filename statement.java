package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import dbcon.database;

/**
 * Servlet implementation class statement
 */

import java.sql.*
;public class statement extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	public void sendemail(String stm){
		
		
		
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter();
		try {
			Connection conn=database.connect();
			Statement s=conn.createStatement();
			Cookie[] ck=request.getCookies();
			String username=ck[0].getValue();
		    ResultSet r=s.executeQuery("select * from "+username);
		    String stm="Statement: ";
		    while(r.next()) {stm+="<br>";
		    		stm+=r.getString(1);}
			out.println(stm);
			sendemail(stm);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

}
