package br.com.model.input;

import java.util.ArrayList;

import br.com.model.bean.PlayerMO;

public class Team {

	private int id;
	private String name;
	private ArrayList<PlayerMO> players = new ArrayList<PlayerMO>();
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
	public ArrayList<PlayerMO> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<PlayerMO> players) {
		this.players = players;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}	
	
}
