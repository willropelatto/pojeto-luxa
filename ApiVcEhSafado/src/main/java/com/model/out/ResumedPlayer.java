package com.model.out;

public class ResumedPlayer {
	
    private String id;
	private String position;
    private String name;
    private int baseId;
    private int rating;
    private boolean hasBid;
    

	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBaseId() {
		return baseId;
	}
	public void setBaseId(int baseId) {
		this.baseId = baseId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}  
	
	public boolean isHasBid() {
		return hasBid;
	}
	public void setHasBid(boolean hasBid) {
		this.hasBid = hasBid;
	}

}
