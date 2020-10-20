package modelManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.database.connectDB;
import models.OrderCustomer;

public class OrderCusManager {
	String sql;
	Connection conn_ = connectDB.getConnect();
	public List<OrderCustomer> getAllOrderCus(){
		List<OrderCustomer> orderCusList = new ArrayList<OrderCustomer>();
		sql = "select * from ordercustomer";
		try{
		Statement statement = conn_.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		OrderCustomer oc = null;
		while(resultSet.next()){
			oc = new OrderCustomer();
			oc.setId(resultSet.getInt("id"));
			oc.setCusName(resultSet.getString("cusName"));
			oc.setEmail(resultSet.getString("email"));
			oc.setAddress(resultSet.getString("address"));
			oc.setPhone(resultSet.getString("phone"));
			oc.setDescription(resultSet.getString("description"));
			orderCusList.add(oc);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return orderCusList;
	}
	
	public OrderCustomer getById(String id){
		sql = "select * from ordercustomer where id ="+id;
		OrderCustomer oc = null;
		
		try {
			Statement statement = conn_.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()){
				oc = new OrderCustomer();
				oc.setId(rs.getInt(1));
				oc.setCusName(rs.getString(2));
				oc.setEmail(rs.getString(3));
				oc.setAddress(rs.getString(4));
				oc.setPhone(rs.getString(5));
				oc.setDescription(rs.getString(6));
				
			}
			
			statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return oc;
	}
	
	public void deleteId(int id) {
		sql = "delete from ordercustomer where id = ?";
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

	// update food by Id
	public  void updateId(int id, OrderCustomer oc) {
		sql = "update ordercustomer set proName = ?, email = ?,address=?,phone=?,description=? where id = ?";
		PreparedStatement pst = null;
		try {
			pst = conn_.prepareStatement(sql);
			pst.setString(1, oc.getCusName());
			pst.setString(2, oc.getEmail());
			pst.setString(3, oc.getAddress());
			pst.setString(4, oc.getPhone());
			pst.setString(5, oc.getDescription());
			pst.setInt(6, id);
			
			if (pst.executeUpdate() > 0) {
				System.out.println("update success");
			} else {
				System.out.println("update error \n");
			}
		} catch (SQLException e) {
			System.out.println("update error \n" + e.toString());
		}
	}
	
	public int  update(OrderCustomer oc) {
		sql = "update ordercustomer set cusName=N'"+oc.getCusName()+"', email=N'"+oc.getEmail()+"'"
				+ ", description=N'"+oc.getDescription()+"', address=N'"+oc.getAddress()+"',phone="+oc.getPhone()+" where id = "+oc.getId();
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
	
	public int  insert(OrderCustomer oc){
		sql="insert into ordercustomer (cusName, email,address,phone,description) "
				+ "values(N'"+oc.getCusName()+"',N'"+oc.getEmail()+"',"
				+ "N'"+oc.getAddress()+"',"
				+ "N'"+oc.getPhone()+"',"
				+ "N'"+oc.getDescription()+"')";
		String sql2="SELECT id FROM ordercustomer ORDER BY id  DESC LIMIT 1";
		int lastId=0;
		try {
			new connectDB();
			Statement statement = connectDB.getConnect().createStatement();
			statement.executeUpdate(sql);
			ResultSet rs = statement.executeQuery(sql2);
			if(rs.next())
				lastId = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------------------lastId:"+lastId);
		return lastId;
	}
	
	public static void main(String[] args) {
		OrderCusManager ocm= new OrderCusManager();
		for(OrderCustomer oc : ocm.getAllOrderCus()){
			System.out.println(oc.getCusName());
		}
		System.out.println(ocm.getById("10"));
	}
}
