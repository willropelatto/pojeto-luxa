package com.model.out;

public class BidInfo {

	private int playerID;
	private double bidValue;
	private int teamID;
	private double originalValue;
	private boolean bidAproved;
	
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public double getBidValue() {
		return bidValue;
	}
	public void setBidValue(double bidValue) {
		this.bidValue = bidValue;
	}
	public int getTeamID() {
		return teamID;
	}
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	public double getOriginalValue() {
		return originalValue;
	}
	public void setOriginalValue(double originalValue) {
		this.originalValue = originalValue;
	}
	
	public BidInfo() {
		this.playerID = 0;
		this.bidValue = 0;
		this.teamID = 0;
		this.originalValue = 0;
		this.bidAproved = false;
	}
	public boolean isBidAproved() {
		return bidAproved;
	}
	public void setBidAproved(boolean bidAproved) {
		this.bidAproved = bidAproved;
	}
	
	
}

