package com.model.out;

public class Transfermarket {
	
    private String idPlayer;
	private String position;
    private String name;
    private int rating;
    private float idBid;
    private float bidValue;
    private int teamId;
    private float originalValue;
    private int playerId;
    private boolean bidAproved;
  
	public String getIdPlayer() {
		return idPlayer;
	}
	public void setIdPlayer(String idPlayer) {
		this.idPlayer = idPlayer;
	}
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
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public float getIdBid() {
		return idBid;
	}
	public void setIdBid(float idBid) {
		this.idBid = idBid;
	}
	public float getBidValue() {
		return bidValue;
	}
	public void setBidValue(float bidValue) {
		this.bidValue = bidValue;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public float getOriginalValue() {
		return originalValue;
	}
	public void setOriginalValue(float originalValue) {
		this.originalValue = originalValue;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public boolean isBidAproved() {
		return bidAproved;
	}
	public void setBidAproved(boolean bidAproved) {
		this.bidAproved = bidAproved;
	}


}
