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
 
	String encrypt(String enp,String salt) {
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
			ResultSet r= s.executeQuery("select * from data where username="+"\""+request.getParameter("username")+"\"");
			if(r.next()) {
			if(r.getString(1).equals(request.getParameter("username"))) {
				//update password
				String salt=r.getString(4);
				if (salt.equals(request.getParameter("salt"))){
				s.executeUpdate("update data set password=\""+encrypt(request.getParameter("password"),salt)+"\" where username=\""+request.getParameter("username")+"\";");
				out.println("Password reset sucess :");}
				else {
					out.println("Incorrect details plz enter correct details");
				}
				
			}}
			else {
			
				//create user
				String salt=request.getParameter("salt");
				s.executeUpdate("insert  into data (username, password,salt) values (\""+request.getParameter("username")+"\" , \""+encrypt(request.getParameter("password"),salt)+"\" , \""+request.getParameter("salt")+"\");");
			//conn.commit();
			s.executeUpdate("create table "+request.getParameter("username")+" (statement varchar(100));");
			conn.close();
			out.println("User is created added");
			request.getRequestDispatcher("index.html").include(request, response);
			}}catch(Exception e) {out.println(e.getMessage());}
		
	}

}
