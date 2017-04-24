package com.model.team;

import com.model.player.ResumedPlayer;

public class Team {

	private int id;
	private String name;
	private ResumedPlayer[] players;
	private String luxa;
	private double Budget;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ResumedPlayer[] getPlayers() {
		return players;
	}
	public void setPlayers(ResumedPlayer[] players) {
		this.players = players;
	}
	public String getLuxa() {
		return luxa;
	}
	public void setLuxa(String luxa) {
		this.luxa = luxa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBudget() {
		return Budget;
	}
	public void setBudget(double budget) {
		Budget = budget;
	}	
}
