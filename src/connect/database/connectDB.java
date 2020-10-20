package connect.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class connectDB {
	static Connection conn_;

	public static Connection getConnect() {
		ResourceBundle rb = ResourceBundle.getBundle("QLNH");
		try {
			Class.forName(rb.getString("driver")).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (conn_ == null || conn_.isClosed()) {
				try {
					conn_ = DriverManager.getConnection(
							rb.getString("url") + rb.getString("databaseName") + rb.getString("uniCode"),
							rb.getString("userName"), rb.getString("password"));
					System.out.println("Ket noi csdl thanh cong");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn_;
	}
}
