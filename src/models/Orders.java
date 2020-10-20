package models;


public class Orders {
	private int id;
	private String deliveryDate;
	private String closeDate;
	private String odStatus;
	private int orderCusId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	public String getOdStatus() {
		return odStatus;
	}
	public void setOdStatus(String odStatus) {
		this.odStatus = odStatus;
	}
	public int getOrderCusId() {
		return orderCusId;
	}
	public void setOrderCusId(int orderCusId) {
		this.orderCusId = orderCusId;
	}
	
}
