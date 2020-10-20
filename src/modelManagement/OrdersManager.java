package modelManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.database.connectDB;
import models.Orders;

public class OrdersManager {
	String sql;
	Connection conn_ = connectDB.getConnect();

	public List<Orders> getAllOrders() {
		List<Orders> ordersList = new ArrayList<Orders>();
		sql = "select * from orders";
		try {
			Statement statement = conn_.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			Orders os = null;
			while (resultSet.next()) {
				os = new Orders();
				os.setId(resultSet.getInt("id"));
				os.setDeliveryDate(resultSet.getString("deliveryDate"));
				os.setCloseDate(resultSet.getString("closeDate"));
				os.setOdStatus(resultSet.getString("odStatus"));
				os.setOrderCusId(resultSet.getInt("orderCusId"));
				ordersList.add(os);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ordersList;
	}

	public Orders getById(String id) {
		sql = "select * from orders where id =" + id;
		Orders os = null;

		try {
			Statement statement = conn_.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()) {
				os = new Orders();
				os.setId(rs.getInt(1));
				os.setDeliveryDate(rs.getString(2));
				os.setCloseDate(rs.getString(3));
				os.setOdStatus(rs.getString(4));

			}

			statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return os;
	}

	public void deleteId(int id) {
		sql = "delete from orders where id = ?";
		PreparedStatement pst = null;
		try {
			pst = conn_.prepareStatement(sql);
			pst.setInt(1, id);
			if (pst.executeUpdate() > 0) {
				System.out.println("delete success");
			} else {
				System.out.println("delete error \n");
			}
		} catch (SQLException e) {
			System.out.println("delete error \n" + e.toString());
		}
	}

	
	public int update(String id,Orders os) {
		sql = "update orders set deliveryDate=N'"+ os.getDeliveryDate()
				+"', closeDate=N'"+os.getCloseDate()
				+"',odStatus=N'"+os.getOdStatus()
				+"' where id="+id;
		System.out.println("--------------os.delDate:"+os.getDeliveryDate());
		try {
			new connectDB();
			Statement statement = connectDB.getConnect().createStatement();
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int insert(Orders os) {
		sql="insert into orders (deliveryDate,closeDate,odStatus,orderCusId) "
				+ "values(N'"+os.getDeliveryDate()+"',"
				+ "N'"+os.getCloseDate()+"',"
				+ "N'"+os.getOdStatus()+"',"
				+ "N'"+os.getOrderCusId()+"')";
		String sql2 = "SELECT id FROM orders ORDER BY id  DESC LIMIT 1";
		int lastOdId= 0;
		System.out.println("---------id:"+lastOdId);
		try {
			new connectDB();
			Statement statement = connectDB.getConnect().createStatement();
			statement.executeUpdate(sql);
			ResultSet rs = statement.executeQuery(sql2);
			if(rs.next())
				lastOdId = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastOdId;
	}
	public static void main(String[] args) {
			OrdersManager osm= new OrdersManager();
			for(Orders os : osm.getAllOrders()){
				System.out.println(os.getDeliveryDate());
			}
			osm.getById("1");
		}
}
