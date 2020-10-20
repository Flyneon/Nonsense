package modelManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import connect.database.connectDB;
import models.Food;
public class FoodManagement {
	String sql;
	Connection conn_ = connectDB.getConnect();
	public List<Food> getAllFood(){
		List<Food> foodList = new ArrayList<Food>();
		sql = "select * from fooddetail order by foodGroupId";
		try{
		Statement statement = conn_.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		Food fd = null;
		while(resultSet.next()){
			fd = new Food();
			fd.setId(resultSet.getInt("id"));
			fd.setFoodName(resultSet.getString("foodName"));
			fd.setImage(resultSet.getString("image"));
			fd.setCost(resultSet.getInt("cost"));
			fd.setFoodGroupId(resultSet.getInt("foodGroupId"));
			foodList.add(fd);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return foodList;
	}
	
	public Food getById(String id){
		sql = "select * from fooddetail where id ="+id;
		Food fd = null;
		
		try {
			Statement statement = conn_.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()){
				fd = new Food();
				fd.setId(rs.getInt(1));
				fd.setFoodName(rs.getString(2));
				fd.setImage(rs.getString(3));
				fd.setCost(rs.getInt(4));
				fd.setFoodGroupId(rs.getInt(5));
				
			}
			
			statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fd;
	}
	
	public void deleteId(String id) {
		sql = "delete from fooddetail where id = ?";
		PreparedStatement pst = null;
		try {
			pst = conn_.prepareStatement(sql);
			pst.setString(1, id);
			if (pst.executeUpdate() > 0) {
				System.out.println("delete success");
			} else {
				System.out.println("delete error \n");
			}
		} catch (SQLException e) {
			System.out.println("delete error \n" + e.toString());
		}
	}

	public  void updateId(int id, Food fd) {
		sql = "update fooddetail set foodName = ?, image = ?,cost=?,foodGroupId=? where id = ?";
		PreparedStatement pst = null;
		try {
			pst = conn_.prepareStatement(sql);
			pst.setString(1, fd.getFoodName());
			pst.setString(2, fd.getImage());
			pst.setInt(3, fd.getCost());
			pst.setInt(4, fd.getFoodGroupId());
			pst.setInt(5, fd.getId());

			if (pst.executeUpdate() > 0) {
				System.out.println("update success");
			} else {
				System.out.println("update error \n");
			}
		} catch (SQLException e) {
			System.out.println("update error \n" + e.toString());
		}
	}
	
	public int  update(Food fd) {
		sql = "update fooddetail set foodName=N'"+fd.getFoodName()+"', image=N'"+fd.getImage()+"'"
				+ ", cost=N'"+fd.getCost()+"', foodGroupId=N'"+fd.getFoodGroupId()+"' where id = "+fd.getId();
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
	
	public int  insert(Food fd){
		sql="insert into fooddetail (foodName, image, cost, foodGroupId) "
				+ "values(N'"+fd.getFoodName()+"',N'"+fd.getImage()+"',"
				+ "N'"+fd.getCost()+"',"
				+ "N'"+fd.getFoodGroupId()+"')";

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
		FoodManagement fm= new FoodManagement();
		for(Food fd : fm.getAllFood()){
			System.out.println(fd.getFoodName());
		}
		System.out.println(fm.getById("1"));

	}
}
