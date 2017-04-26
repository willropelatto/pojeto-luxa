package com.ctrl.game;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.model.league.LeagueEntity;
import com.model.player.League;
import com.model.player.PlayerDAO;
import com.model.player.PlayerEntity;
import com.model.player.ResumedPlayer;


@Path("/player")
public class PlayerRestController {	 

	private final PlayerController ctrl = new PlayerController(); 

	/**
	 * Esse método busca uma pessoa cadastrada pelo código
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getPlayerFromLeague/{league}")
	public List<ResumedPlayer> getPlayerFromLeague(@PathParam("league") Integer league){
 
		PlayerDAO pdao = new PlayerDAO();
		List<PlayerEntity> enPlayers = pdao.getPlayerFromLeague(league);				
		return ctrl.convertListEntityToResumed(enPlayers);
		
	}




}