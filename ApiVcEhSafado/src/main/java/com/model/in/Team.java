package com.model.in;

import java.util.ArrayList;

import com.model.out.ResumedPlayer;

public class Team {

	private int id;
	private String name;
	private ArrayList<ResumedPlayer> players = new ArrayList<ResumedPlayer>();
	private String manager;
	private double budget;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String luxa) {
		this.manager = luxa;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public ArrayList<ResumedPlayer> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<ResumedPlayer> players) {
		this.players = players;
	}	
}
