package com.view.game;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ctrl.game.TeamController;
import com.model.in.Team;

@Path("/team")
public class TeamRest {
	
	private final TeamController ctrl = new TeamController(); 

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/list")
	public List<Team> getTeams(){

		List<Team> list = ctrl.getTeams();
		return list;
	}
	
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/register")
	public int register(Team team) {
		ctrl.registerTeam(team);
		return 0; //TODO ver de caputrar exceção para mandar 1 quando for erro.
	}
	
	
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/update")
	public int update(Team team) {
		ctrl.updateTeam(team);
		return 0; //TODO ver de caputrar exceção para mandar 1 quando for erro.
	}

}
