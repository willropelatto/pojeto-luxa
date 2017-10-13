package br.com.model.bean;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Bids")
public class BidMO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;	
	@Column(name="playerId")
	private int playerId;
	@Column(name="bidValue")
	private double bidValue;
	@Column(name="teamId")
	private int teamId;
	@Column(name="originalValue")
	private double originalValue;
	@Column(name="playerName")
	private String playerName;
	@Column(name="bidTime")
	private LocalDateTime bidTime;
	@Column(name="position")
	private String position;
	
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getPlayerID() {
		return playerId;
	}
	public void setPlayerID(int playerID) {
		this.playerId = playerID;
	}
	public double getBidValue() {
		return bidValue;
	}
	public void setBidValue(double bidValue) {
		this.bidValue = bidValue;
	}
	public int getTeamID() {
		return teamId;
	}
	public void setTeamID(int teamID) {
		this.teamId = teamID;
	}
	public double getOriginalValue() {
		return originalValue;
	}
	public void setOriginalValue(double originalValue) {
		this.originalValue = originalValue;
	}
	public LocalDateTime getBidTime() {
		return bidTime;
	}
	public void setBidTime(LocalDateTime bidTime) {
		this.bidTime = bidTime;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
	
}
