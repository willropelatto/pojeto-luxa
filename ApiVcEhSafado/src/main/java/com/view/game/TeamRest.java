package com.view.game;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ctrl.game.TeamController;
import com.model.in.Team;
import com.model.out.ResumedPlayer;

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
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/listTeam/{idUser}")
	public Team getLeaguePlayers(@PathParam("idUser") Integer idUser){
 			
		return ctrl.getTeamFromUser(idUser);
	}	
	
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/register")
	public Team register(Team team) {
		ctrl.registerTeam(team);
		
		return team; //TODO ver de caputrar exceção para mandar 1 quando for erro.
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
