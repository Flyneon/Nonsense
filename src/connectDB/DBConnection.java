package connectDB;
import java.sql.Connection;
import java.sql.DriverManager;

import connectDB.DBConnection;
public class DBConnection {
	private static Connection CONNECTION_ = null;

	public DBConnection(){
		try{
			if(CONNECTION_ == null || CONNECTION_.isClosed()){
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				CONNECTION_ = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
						+  "khachhang?useUnicode=true&characterEncoding=UTF-8",
						"root", "123456");
				System.out.println("Ket noi csdl thanh cong");						
			} 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		return CONNECTION_;
	}

	public static void main(String[] args) {
		DBConnection dbc= new DBConnection();
		System.out.println("Finish");
	}
}
