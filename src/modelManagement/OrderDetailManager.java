package modelManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.database.connectDB;
import models.OrderDetail;

public class OrderDetailManager {
	String sql;
	Connection conn_ = connectDB.getConnect();

	public List<OrderDetail> getAllOrderDetail() {
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		sql = "select * from orderdetail";
		try {
			Statement statement = conn_.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			OrderDetail od = null;
			while (resultSet.next()) {
				od = new OrderDetail();
				od.setId(resultSet.getInt("id"));
				od.setFoodId(resultSet.getInt("foodId"));
				od.setNumber(resultSet.getInt("number"));
				od.setTotalCost(resultSet.getInt("totalCost"));
				od.setCusName(resultSet.getString("cusName"));
				od.setEmail(resultSet.getString("email"));
				od.setAddress(resultSet.getString("address"));
				od.setPhone(resultSet.getString("phone"));
				od.setDescription(resultSet.getString("description"));
				od.setOrdersId(resultSet.getInt("ordersId"));
				orderDetailList.add(od);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderDetailList;
	}

	public OrderDetail getById(String id) {
		sql = "select * from orderdetail where id =" + id;
		OrderDetail od = null;

		try {
			Statement statement = conn_.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()) {
				od = new OrderDetail();
				od.setId(rs.getInt(1));
				od.setFoodId(rs.getInt(2));
				od.setNumber(rs.getInt(3));
				od.setTotalCost(rs.getInt(4));
				od.setCusName(rs.getString(5));
				od.setEmail(rs.getString(6));
				od.setAddress(rs.getString(7));
				od.setPhone(rs.getString(8));
				od.setDescription(rs.getString(9));
			}

			statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return od;
	}

	public void deleteId(int id) {
		sql = "delete from orderdetail where id = ?";
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

	public int update(OrderDetail od) {
		sql = "update ordercustomer set foodId=N'" + od.getFoodId() + "', number=N'" + od.getNumber() + "'"
				+ ", totalCost=N'" + od.getTotalCost() + "', " 
				+"cusName=N'"+od.getCusName()+"', email=N'"+od.getEmail()+"'"
				+ ", description=N'"+od.getDescription()+"', address=N'"+od.getAddress()+"',phone="+od.getPhone()
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

	public int insert(OrderDetail od) {
		sql = "insert into orderdetail (foodId, number,totalCost,orderDate,cusName, email,address,phone,description,ordersId) " + "values(N'" + od.getFoodId()
				+ "',N'" + od.getNumber() + "'," 
				+ "N'" + od.getTotalCost() + "'," 
				+ "N'"+od.getCusName()+"',"
				+ "N'"+od.getEmail()+"',"
				+ "N'"+od.getAddress()+"',"
				+ "N'"+od.getPhone()+"',"
				+ "N'"+od.getDescription() + "')";
		

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
			OrderDetailManager odm= new OrderDetailManager();
			
			odm.getById("1");
		}
}
