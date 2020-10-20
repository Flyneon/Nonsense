package models;

import java.sql.Date;

public class ModelsKhachHang {
	private int soban;
	private int idK;
	private int idB;
	private String ten;
	private String email;
	private String sdt;
	private String mota;
	private Date ngay;
	private int buoi;
	private int soNguoi;
	private int datCoc;
	private int tongTien;
	private int ban;
	public int getSoban() {
		return 8;
	}	
	public int getIdK() {
		return idK;
	}
	public void setIdK(int idK) {
		this.idK = idK;
	}
	public int getIdB() {
		return idB;
	}
	public void setIdB(int idB) {
		this.idB = idB;
	}
	
	public int getBan() {
		return ban;
	}
	public void setBan(int ban) {
		this.ban = ban;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public Date getNgay() {
		return ngay;
	}
	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
	public int getBuoi() {
		return buoi;
	}
	public void setBuoi(int buoi) {
		this.buoi = buoi;
	}
	public int getSoNguoi() {
		return soNguoi;
	}
	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
	}
	public int getDatCoc() {
		return datCoc;
	}
	public void setDatCoc(int datCoc) {
		this.datCoc = datCoc;
	}
	public int getTongTien() {
		return tongTien;
	}
	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}
	
}
