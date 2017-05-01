package com.model.in;

import java.util.ArrayList;

import com.model.out.ResumedPlayer;

public class Team {

	private int id;
	private String name;
	private ArrayList<ResumedPlayer> players = new ArrayList<ResumedPlayer>();
	private String luxa;
	private double Budget;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public ArrayList<ResumedPlayer> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<ResumedPlayer> players) {
		this.players = players;
	}	
}
