package br.com.pofexo.ctrl;

import br.com.pofexo.model.bean.NotificationMO;
import br.com.pofexo.model.bean.PlayerMO;

public class Message {
	
	private String to;
	private String message;
	private int teamId;
	private String nameTeam;
	private String playerName;
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getNameTeam() {
		return nameTeam;
	}
	public void setNameTeam(String nameTeam) {
		this.nameTeam = nameTeam;
	}
	
	public Message(PlayerMO player, NotificationMO ntf) {
		this.message = ntf.getNotification();
		this.nameTeam = player.getTeam().getName();
		this.playerName = ntf.getPlayerName();
		this.teamId = ntf.getTeamId();
		this.to = player.getTeam().getManager();
	}
	public Message() {
		
	}
}
