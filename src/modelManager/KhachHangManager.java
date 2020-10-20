package modelManager;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import connectDB.DBConnection;
import models.ModelsKhachHang;

public class KhachHangManager {
	@SuppressWarnings("null")
	public int nhapKhachHang(String ten, String email, String sdt) {
		int idK = 0;
		DBConnection conn = new DBConnection();
		String sql = "select * from thongtinkhachhang "
				+ "where ten = '"+ten+"' and email = '"+email+"' and sdt = '"+sdt+"'";
		System.out.println(sql);
		try {			
			Statement statement = (Statement) conn.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()){
				idK=rs.getInt("idK");
				String sql2 = "update thongtinkhachhang set mota = "+"'Khach hang tiem nang'"+" where idK = '"+rs.getString(1)+"'";
				statement.executeUpdate(sql2);
			}else{
				String sql2 = "insert into thongtinkhachhang(ten, email, sdt, mota) "
						+ "value('"+ten+"', '"+email+"', '"+sdt+"', "+"'Khach hang vang lai'"+")";
				statement.executeUpdate(sql2);
				String sql3 = "select * from thongtinkhachhang "
						+ "where ten = '"+ten+"' and email = '"+email+"' and sdt = '"+sdt+"'";
				rs = statement.executeQuery(sql3);
				if(rs.next()) idK = rs.getInt("idK");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idK;
	}
	public int xacNhanBan(String ngay, String buoi){
		int soBan=0;
		ModelsKhachHang ban = new ModelsKhachHang();
		DBConnection conn = new DBConnection();
		String sql = null;
		String str = "1";
		try {
			String countTable;
			if(buoi.equals(str)){
				sql = "select * from thongtinban "
						+ "where ngay = '"+ngay+"' and trc13h = '"+"1"+"'";				
			}else{
				sql = "select * from thongtinban "
						+ "where ngay = '"+ngay+"' and sau13h = '"+"1"+"'";	
			}
			System.out.println(sql);
			Statement statement = (Statement) conn.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				soBan+= rs.getInt("soBan");				
			}
			return ban.getSoban() - soBan;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public void nhapThongTinBan(int idD, String soNguoi, String ngay, String trc13h, String idKTrc13h, String sau13h, String idKSau13h) {
		int soBan;
		soBan = Integer.parseInt(soNguoi);
		if(soBan%6>0) soBan = soBan/6+1;
		else soBan=soBan/6;
		int trc13h_sql = 0;
		int sau13h_sql = 0;
		int idKTrc13h_sql =0;
		int idKSau13h_sql =0;
		if(sau13h != null) sau13h_sql = Integer.parseInt(sau13h);
		if(trc13h != null) trc13h_sql = Integer.parseInt(trc13h);
		if(idKTrc13h != null) idKTrc13h_sql = Integer.parseInt(idKTrc13h);
		if(idKSau13h != null) idKSau13h_sql = Integer.parseInt(idKSau13h);
		String sql = "insert into thongTinBan (idD, soBan, ngay, trc13h, idKTrc13h, sau13h, idKSau13h)"
				+ " values ('"+idD+"', '"+soBan+"', '"+ngay+"', '"+trc13h_sql+"', '"+idKTrc13h_sql+"', '"+sau13h_sql+"', '"+idKSau13h_sql+"')";
		System.out.println("Nhap Thong Tin Ban: "+sql);
		DBConnection conn = new DBConnection();
		try {
			Statement statement = (Statement) conn.getConnection().createStatement();
			statement.executeUpdate(sql);
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public int nhapDatTruoc(String idK, String soNguoi, String ngay, String buoi, String tongTien){
		String sql = "insert into dattruoc (idK, soNguoi, ngay, buoi, tongTien) "
				+ "values ('"+idK+"', '"+soNguoi+"', '"+ngay+"', '"+buoi+"', '"+tongTien+"')";
		String sql2 = "select * from dattruoc"
				+ " where idK='"+idK+"' and ngay='"+ngay+"' and buoi='"+buoi+"'";
		System.out.println("Nhap dat truoc: "+sql);
		System.out.println("Nhap dat truoc: "+sql2);
		int idD = 0;
		DBConnection conn = new DBConnection();
		Statement statement;
		try {
			statement = (Statement) conn.getConnection().createStatement();
			statement.executeUpdate(sql);
			ResultSet rs = statement.executeQuery(sql2);
			if(rs.next()) idD = rs.getInt("idD");
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idD;
	}
	public void nhapDatMon(int idD, String monAn, String soluong){
		String sql = "insert into datmon(idD, monAn, soluong)"
				+ " values('"+idD+"', '"+monAn+"', '"+soluong+"')";
		System.out.println("Nhap Dat Mon: "+sql);
		DBConnection conn = new DBConnection();
		Statement statement;
		try {
			statement = (Statement) conn.getConnection().createStatement();
			statement.executeUpdate(sql);
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ModelsKhachHang xacNhanDonHang(int idD){
		DBConnection conn = new DBConnection();
		Statement statement = null;
		ModelsKhachHang dondathang = null;
		String sql="select * from dattruoc where idD = "+idD;
		try {
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()){
				dondathang.setSoNguoi(rs.getInt("soNguoi"));
				dondathang.setNgay(rs.getDate("ngay"));
				dondathang.setBuoi(rs.getInt("buoi"));
				dondathang.setIdK(rs.getInt("idK"));
				return dondathang;
			}else return dondathang;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dondathang;
	}
	public void xoaMon(int idD){
		String sql = "delete from datmon where idD = "+idD;
		DBConnection conn = new DBConnection();
		Statement statement = null;
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateDatTruoc(int idD, String idK, String soNguoi, String ngay, String buoi, String tongTien){
		String sql ="update dattruoc set idK = "+idK+", soNguoi = "+soNguoi+", ngay = "+ngay+", "
				+ "buoi = "+buoi+", tongTien = "+tongTien+" where idD = "+idD;
		DBConnection conn = new DBConnection();
		Statement statement=null;
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		KhachHangManager test = new KhachHangManager();
		test.nhapKhachHang("Phạm Hoàng Hải", "h@gmail.com", "123456");
		System.out.println(test.xacNhanBan("2016-08-17", "1"));
		test.nhapThongTinBan(1, "13", "2016-08-20", "1", "1", "0", "0");
		test.nhapDatTruoc("1", "6", "2016-08-20", "1", "721000");
		test.nhapDatMon(1, "Nem", "2");
	}
}
