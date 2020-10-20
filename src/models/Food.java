package models;

public class Food {
	private int id;
	private String foodName;
	private String image;
	private int cost;
	private int foodGroupId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost ;
	}

	public int getFoodGroupId() {
		return foodGroupId;
	}

	public void setFoodGroupId(int foodGroupId) {
		this.foodGroupId = foodGroupId;
	}

}
