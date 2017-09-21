package br.com.model;

import java.io.Serializable;

public class PlayerAttributeAssociationId implements Serializable {

	private static final long serialVersionUID = 9048251604261478775L;
	private int attributeId;
	private int playerId;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int hashCode() {
		return (int) (attributeId + playerId);
	}

	public boolean equals(Object object) {
		if (object instanceof PlayerAttributeAssociationId) {
			PlayerAttributeAssociationId otherId = (PlayerAttributeAssociationId) object;
			return (otherId.attributeId == this.attributeId) && (otherId.playerId == this.playerId);
		}
		return false;
	}

}
