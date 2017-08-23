package com.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_bidLog")
public class BidEntityLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;	
	@Column(name="playerID")
	private int playerID;
	@Column(name="bidValue")
	private double bidValue;
	@Column(name="teamID")
	private int teamID;
	@Column(name="originalValue")
	private double originalValue;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	
	
}
