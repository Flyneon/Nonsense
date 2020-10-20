package modelManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.database.connectDB;
import models.OrderDetail2;

public class OrderDetail2Manager {
	String sql;
	Connection conn_ = connectDB.getConnect();

	public List<OrderDetail2> getAllOrderDetail() {
		List<OrderDetail2> orderDetailList = new ArrayList<OrderDetail2>();
		sql = "select * from orderdetail2";
		try {
			Statement statement = conn_.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			OrderDetail2 od = null;
			while (resultSet.next()) {
				od = new OrderDetail2();
				od.setId(resultSet.getInt("id"));
				od.setFoodId(resultSet.getInt("foodId"));
				od.setNumber(resultSet.getInt("number"));
				od.setTotalCost(resultSet.getInt("totalCost"));
				od.setOrdersId(resultSet.getInt("ordersId"));
				od.setOrderDate(resultSet.getString("orderDate"));
				orderDetailList.add(od);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderDetailList;
	}

	public OrderDetail2 getById(String id) {
		sql = "select * from orderdetail2 where id =" + id;
		OrderDetail2 od = null;

		try {
			Statement statement = conn_.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()) {
				od = new OrderDetail2();
				od.setId(rs.getInt(1));
				od.setFoodId(rs.getInt(2));
				od.setNumber(rs.getInt(3));
				od.setTotalCost(rs.getInt(4));
				od.setOrdersId(rs.getInt(5));
				od.setOrderDate(rs.getString(6));
			}	

			statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return od;
	}

	public void deleteId(int id) {
		sql = "delete from orderdetail2 where id = ?";
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

	public int update(OrderDetail2 od) {
		sql = "update orderdetail2 set foodId=N'" + od.getFoodId() + "', number=N'" + od.getNumber() + "'"
				+ ", totalCost=N'" + od.getTotalCost() + "', " 
				+",ordersId"+od.getOrdersId()
				+",orderDate"+od.getOrderDate()
				+ " where id = " + od.getId();
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

	public int insert(OrderDetail2 od) {
		sql="insert into orderdetail2 (foodId, number,totalCost,orderDate,ordersId) "
				+ "values(N'"+od.getFoodId()+"',N'"+od.getNumber()+"',"
				+ "N'"+od.getTotalCost()+"',"
				+ "N'"+od.getOrderDate()+"',"
				+ "N'"+od.getOrdersId()+ "')";
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

	public static void main(String[] args) {
		}
}
