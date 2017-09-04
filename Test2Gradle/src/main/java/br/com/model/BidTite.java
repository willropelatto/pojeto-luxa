package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BidTite {

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
	
	
}
