package modelManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.database.connectDB;
import models.TypeNews;

public class TypeNewsManager {
	String sql;
	Connection conn_ = connectDB.getConnect();
	public List<TypeNews> getAllNews(){
		List<TypeNews> typeNewsList = new ArrayList<TypeNews>();
		sql = "select * from typenews";
		try{
		Statement statement = conn_.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		TypeNews tn = null;
		while(resultSet.next()){
			tn = new TypeNews();
			tn.setId(resultSet.getInt("id"));
			tn.setTypeNewsName(resultSet.getString("typeNewsName"));
			typeNewsList.add(tn);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return typeNewsList;
	}
	
	public TypeNews getById(String id){
		sql = "select * from typenews where id ="+id;
		TypeNews tn = null;
		
		try {
			Statement statement = conn_.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()){
				tn = new TypeNews();
				tn.setId(rs.getInt(1));
				tn.setTypeNewsName(rs.getString(2));
			}
			
			statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tn;
	}
	
	public void deleteId(String id) {
		sql = "delete from typenews where id = ?";
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
	
	public void updateId(String id, String typeNewsName){
		sql = "update typenews set typeNewsName = N'"+typeNewsName+"' where id = " + id;
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
	
	public boolean insert(String typeNewsName){
		sql = "insert into typenews(typeNewsName) values(N'" + typeNewsName + "')";
		
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
		TypeNewsManager tnm = new TypeNewsManager();
		for(TypeNews tn : tnm.getAllNews()){
			System.out.println(tn.getTypeNewsName());
		}
	}
}
