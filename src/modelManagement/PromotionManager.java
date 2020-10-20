package modelManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.database.connectDB;
import models.Promotion;

public class PromotionManager {
	String sql;
	Connection conn_ = connectDB.getConnect();
	public List<Promotion> getAllPromotion(){
		List<Promotion> promotionList = new ArrayList<Promotion>();
		sql = "select * from promotion order by id asc";
		try{
		Statement statement = conn_.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		Promotion pr = null;
		while(resultSet.next()){
			pr = new Promotion();
			pr.setId(resultSet.getInt("id"));
			pr.setProName(resultSet.getString("proName"));
			pr.setBrief(resultSet.getString("brief"));
			pr.setDescription(resultSet.getString("description"));
			pr.setProStatus(resultSet.getInt("proStatus"));
			pr.setTypeNewsId(resultSet.getInt("typeNewsId"));
			pr.setImages(resultSet.getString("images"));
			promotionList.add(pr);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return promotionList;
	}
	
	public Promotion getById(String id){
		sql = "select * from promotion where id ="+id;
		Promotion pt = null;
		
		try {
			Statement statement = conn_.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()){
				pt = new Promotion();
				pt.setId(rs.getInt(1));
				pt.setProName(rs.getString(2));
				pt.setBrief(rs.getString(3));
				pt.setDescription(rs.getString(4));
				pt.setProStatus(rs.getInt(5));
				pt.setTypeNewsId(rs.getInt(6));
				pt.setImages(rs.getString(7));
			}
			
			statement.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pt;
	}
	
	public void deleteId(String id) {
		sql = "delete from promotion where id = ?";
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

	
	public int  update( String id,Promotion pr) {
		sql = "update promotion set proName=N'"+pr.getProName()+"', brief=N'"+pr.getBrief()+"'"
				+ ", description=N'"+pr.getDescription()+"', proStatus=N'"+pr.getProStatus()+"',typeNewsId="+pr.getTypeNewsId()+",images=N'"+pr.getImages()+"' where id = "+id;
		try {
			System.out.println(""+sql);
			new connectDB();
			Statement statement = connectDB.getConnect().createStatement();
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int  insert(Promotion pr){
		sql="insert into promotion (proName, brief, description, proStatus,typeNewsId,images) "
				+ "values(N'"+pr.getProName()+"',N'"+pr.getBrief()+"',"
				+ "N'"+pr.getDescription()+"',"
				+ "N'"+pr.getProStatus()+"',"
				+ "N'"+pr.getTypeNewsId()
				+ "N'"+pr.getImages()+"')";

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
		PromotionManager pm= new PromotionManager();
		for(Promotion pr : pm.getAllPromotion()){
			System.out.println(pr.getProName());
		}
		System.out.println(pm.getById("1"));
		pm.getById("1");
	}
}
