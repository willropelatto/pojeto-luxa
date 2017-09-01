package br.com.team.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.player.model.PlayerTite;

@Entity
public class TeamTite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="idcup")
	private Integer idcup;
	@Column(name="manager")
	private String manager;
	@Column(name="budget")
	private double budget;
	@Column(name="idUser")
	private Integer idUser;

	@OneToMany(mappedBy="team")	
	private List<PlayerTite> players;	


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIdcup() {
		return idcup;
	}
	public void setIdcup(Integer idcup) {
		this.idcup = idcup;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public List<PlayerTite> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerTite> players) {
		this.players = players;
	}	

}
