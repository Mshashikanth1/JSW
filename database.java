package dbcon;
import java.sql.*;
public class database {

	public static   Connection connect() throws SQLException , ClassNotFoundException {
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useSSL=false","root","Mshashikanth@1");
			/*Statement s=conn.createStatement();
			System.out.println(conn);
			ResultSet r= s.executeQuery("select * from data");
			
			while(r.next()) {
				System.out.println(r.getString(1)+" "+r.getString(2)+"\n");
		
				}
			conn.close();*/
			
			return conn;		
		
	
	}
		

}
