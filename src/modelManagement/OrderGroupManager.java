package modelManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.database.connectDB;
import models.OrderGroup;

public class OrderGroupManager {
	String sql;
	Connection conn_ = connectDB.getConnect();
	
	public List<OrderGroup> getAllGroup(){
		List<OrderGroup> orderGroupList = new ArrayList<OrderGroup>();
		sql = "SELECT os.id, od.orderDate,os.deliveryDate,os.closeDate,os.odStatus,oc.cusName,oc.email,oc.address ,oc.phone,oc.description,fd.foodName,od.number,od.totalCost"
		+" FROM orders AS os,ordercustomer AS oc ,orderdetail2 AS od,fooddetail AS fd "
				+"WHERE oc.id = os.orderCusId and od.foodId = fd.id and od.ordersId = os.id";
		try{
		Statement statement = conn_.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		OrderGroup og = null;
		while(rs.next()){
			og = new OrderGroup();
			og.setId(rs.getInt("id"));
			og.setOrderDate(rs.getString("orderDate"));
			og.setDeliveryDate(rs.getString("deliveryDate"));
			og.setCloseDate(rs.getString("closeDate"));
			og.setOdStatus(rs.getString("odStatus"));
			og.setCusName(rs.getString("cusName"));
			og.setEmail(rs.getString("email"));
			og.setAddress(rs.getString("address"));
			og.setPhone(rs.getString("phone"));
			og.setDescription(rs.getString("description"));
			og.setFoodName(rs.getString("foodName"));
			og.setNumber(rs.getInt("number"));;
			og.setTotalCost(rs.getInt("totalCost"));
			orderGroupList.add(og);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return orderGroupList;
	}
	
	public OrderGroup getById(String id){
		sql = "SELECT os.id, od.orderDate,os.deliveryDate,os.closeDate,os.odStatus,oc.cusName,oc.email,oc.address ,oc.phone,oc.description,fd.foodName,od.number,od.totalCost"
		+" FROM orders AS os,ordercustomer AS oc ,orderdetail2 AS od,fooddetail AS fd "
				+"WHERE oc.id = os.orderCusId and od.foodId = fd.id and od.ordersId = os.id and os.id ="+id;
		OrderGroup og = null;
		
		try {
			Statement statement = conn_.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()){
				og = new OrderGroup();
				og.setId(rs.getInt(1));
				og.setOrderDate(rs.getString(2));
				og.setDeliveryDate(rs.getString(3));
				og.setCloseDate(rs.getString(4));
				og.setOdStatus(rs.getString(5));
				og.setCusName(rs.getString(6));
				og.setEmail(rs.getString(7));
				og.setAddress(rs.getString(8));
				og.setPhone(rs.getString(9));
				og.setDescription(rs.getString(10));
				og.setFoodName(rs.getString(11));
				og.setNumber(rs.getInt(12));
				og.setTotalCost(rs.getInt(13));
				
			}
			
			statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return og;
	}
	
	public void delete(String id){
		sql="delete orders,ordercustomer,orderdetail2 "
				+ "from orders inner join ordercustomer on order.id= orderdetail2.ordersId inner join orderdetail2 "
				+ "where ordercustomer.id= orders.orderCusId and order.id= orderdetail2.ordersId and order.id="+id;
		try {
			new connectDB();
			Statement statement = connectDB.getConnect().createStatement();
			statement.executeQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		OrderGroupManager ogm =new OrderGroupManager();
		for(OrderGroup og: ogm.getAllGroup())
		{
			System.out.println("-------------cusname:"+og.getTotalCost());
		}
		System.out.println(ogm.getById("6"));
	}

}
