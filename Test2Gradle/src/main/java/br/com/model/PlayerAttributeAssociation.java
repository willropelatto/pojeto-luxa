package br.com.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@IdClass(PlayerAttributeAssociationId.class)
public class PlayerAttributeAssociation {

	@Id
	private int attributeId;
	@Id
	private int playerId;

	private int value;

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "attributeId", referencedColumnName = "ID")
	/*
	 * if this JPA model doesn't create a table for the "PROJ_EMP" entity,
	 * please comment out the @PrimaryKeyJoinColumn, and use the ff:
	 * 
	 * @JoinColumn(name = "employeeId", updatable = false, insertable = false)
	 * or @JoinColumn(name = "employeeId", updatable = false, insertable =
	 * false, referencedColumnName = "id")
	 */
	private PlayerAttributes attribute;
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "playerId", referencedColumnName = "ID")
	/*
	 * the same goes here: if this JPA model doesn't create a table for the
	 * "PROJ_EMP" entity, please comment out the @PrimaryKeyJoinColumn, and use
	 * the ff:
	 * 
	 * @JoinColumn(name = "projectId", updatable = false, insertable = false)
	 * or @JoinColumn(name = "projectId", updatable = false, insertable = false,
	 * referencedColumnName = "id")
	 */
	private PlayerTite player;

	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public PlayerAttributes getAttribute() {
		return attribute;
	}

	public void setAttribute(PlayerAttributes attribute) {
		this.attribute = attribute;
	}

	public PlayerTite getPlayer() {
		return player;
	}

	public void setPlayer(PlayerTite player) {
		this.player = player;
	}

}
