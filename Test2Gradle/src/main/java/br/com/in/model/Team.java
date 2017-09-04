package br.com.in.model;

import java.util.ArrayList;

import br.com.model.PlayerTite;

public class Team {

	private int id;
	private String name;
	private ArrayList<PlayerTite> players = new ArrayList<PlayerTite>();
	private String manager;
	private double budget;
	private int idUser;
	
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
	public ArrayList<PlayerTite> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<PlayerTite> players) {
		this.players = players;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}	
	
}
