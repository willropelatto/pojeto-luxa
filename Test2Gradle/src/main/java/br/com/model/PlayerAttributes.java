package br.com.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PlayerAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "attribute")	
	private Set<PlayerAttributeAssociation> players;

	public PlayerAttributes() {
		super();
		this.players = new HashSet<>();
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<PlayerAttributeAssociation> getPlayers() {
		return players;
	}

	public void setPlayers(Set<PlayerAttributeAssociation> players) {
		this.players = players;
	}

}
