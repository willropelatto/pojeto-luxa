package com.ctrl.game;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.model.player.League;


@Path("/league")
public class PlayerRestController {	 

	private final LeagueController ctrl = new LeagueController(); 

	/**
	 * Esse método lista todas pessoas cadastradas na base
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/list")
	public List<League> getLeagues(){

		List<League> leagues = ctrl.ShowLeagues();
		return leagues;
	}




}