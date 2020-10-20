package modelManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.database.connectDB;
import models.FoodGroup;

public class FoodGroupManager {
	String sql;
	Connection conn_ = connectDB.getConnect();
	public List<FoodGroup> getAllFood(){
		List<FoodGroup> foodGroupList = new ArrayList<FoodGroup>();
		sql = "select * from foodgroup";
		try{
		Statement statement = conn_.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		FoodGroup fg = null;
		while(resultSet.next()){
			fg = new FoodGroup();
			fg.setId(resultSet.getInt("id"));
			fg.setGroupName(resultSet.getString("groupName"));
			foodGroupList.add(fg);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return foodGroupList;
	}
	
	public FoodGroup getById(String id){
		sql = "select * from foodgroup where id ="+id;
		FoodGroup fg = null;
		
		try {
			Statement statement = conn_.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()){
				fg = new FoodGroup();
				fg.setId(rs.getInt(1));
				fg.setGroupName(rs.getString(2));
			}
			
			statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fg;
	}
	
	public void deleteId(String id) {
		sql = "delete from foodgroup where id = ?";
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

	// update food by Id
	
	public void updateId(String id, String name){
		sql = "update foodgroup set groupName = N'"+name+"' where id = " + id;
		new connectDB();
		try{
			Statement statement = connectDB.getConnect().createStatement();
			statement.executeUpdate(sql);
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean insert(String groupName){
		sql = "insert into foodgroup(groupName) values(N'" + groupName + "')";
		
		new connectDB();
		try {
			Statement statement = connectDB.getConnect().createStatement();
			statement.execute(sql);
			statement.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	public static void main(String[] args) {
		FoodGroupManager gcm = new FoodGroupManager();
		for(FoodGroup gc : gcm.getAllFood()){
			System.out.println(gc.getGroupName());
		}
	}
}
