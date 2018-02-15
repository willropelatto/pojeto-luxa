package br.com.model.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Team")
public class TeamMO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
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

	@OneToMany(mappedBy="team", cascade=CascadeType.ALL)
	@JsonManagedReference(value="team-ref")
	private Set<PlayerMO> players;	

	public TeamMO() {
		super();
		this.players = new HashSet<PlayerMO>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public Set<PlayerMO> getPlayers() {
		return players;
	}
	public void setPlayers(Set<PlayerMO> players) {
		this.players = players;
	}	

}
