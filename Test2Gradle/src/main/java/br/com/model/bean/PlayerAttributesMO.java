package br.com.model.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "PlayersAttributes")
public class PlayerAttributesMO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "attribute")	
	@JsonManagedReference(value="attr-ref")
	private Set<PlayerAttributeAssociationMO> players;

	public PlayerAttributesMO() {
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

	public Set<PlayerAttributeAssociationMO> getPlayers() {
		return players;
	}

	public void setPlayers(Set<PlayerAttributeAssociationMO> players) {
		this.players = players;
	}

}
