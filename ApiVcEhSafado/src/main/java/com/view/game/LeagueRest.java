package com.view.game;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ctrl.game.LeagueController;
import com.model.in.League;

@Path("/league")
public class LeagueRest {

	private final LeagueController ctrl = new LeagueController(); 

	/**
	 * 
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/list")
	public List<League> getLeagues(){

		List<League> leagues = ctrl.ShowLeagues();
		return leagues;
	}
	
}
