package models;

public class Promotion {
	private int id;
	private String proName;
	private String brief;
	private String description;
	private int typeNewsId;
	private int proStatus;
	private String images;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTypeNewsId() {
		return typeNewsId;
	}
	public void setTypeNewsId(int typeNewsId) {
		this.typeNewsId = typeNewsId;
	}
	public int getProStatus() {
		return proStatus;
	}
	public void setProStatus(int proStatus) {
		this.proStatus = proStatus;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	
	
}
