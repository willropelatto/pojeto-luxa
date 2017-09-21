package br.com.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PlayerAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")	
	private Integer id;
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy = "attribute")
	private List<PlayerAttributeAssociation> players;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<PlayerAttributeAssociation> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerAttributeAssociation> players) {
		this.players = players;
	}	
	
}
