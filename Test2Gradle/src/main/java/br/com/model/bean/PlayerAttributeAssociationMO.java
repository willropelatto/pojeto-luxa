package br.com.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "PlayerAttributeAssociation")
public class PlayerAttributeAssociationMO  implements Serializable {

	private static final long serialVersionUID = 1L;
	private PlayerMO player;
    private PlayerAttributesMO attribute;
    
	@Column(name = "value")
	private int value;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id")
	@JsonBackReference(value="player-ref")
	public PlayerMO getPlayer() {
		return player;
	}

	public void setPlayer(PlayerMO player) {
		this.player = player;
	}
	
    @Id
    @ManyToOne
    @JoinColumn(name = "attribute_id")
	@JsonBackReference(value="attr-ref")
	public PlayerAttributesMO getAttribute() {
		return attribute;
	}

	public void setAttribute(PlayerAttributesMO attribute) {
		this.attribute = attribute;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	@Transient
	public String getName() {
		return this.attribute.getName();
	}
	
	@Transient
	public void setName(String name) {
		this.attribute.setName(name);
	}

}
