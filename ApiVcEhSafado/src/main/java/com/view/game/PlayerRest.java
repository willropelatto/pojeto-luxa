package com.view.game;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.ctrl.game.PlayerController;
import com.model.in.PlayerFilter;
import com.model.out.ResumedPlayer;


@Path("/player")
public class PlayerRest {	 

	private final PlayerController ctrl = new PlayerController(); 

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/listPlayer/{league}")
	public List<ResumedPlayer> getLeaguePlayers(@PathParam("league") Integer league){
 			
		return ctrl.getLeaguePlayers(league);		
	}	
	
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getPlayer/{playerId}")
	public ResumedPlayer getPlayerFromId(@PathParam("playerId") Integer playerId){
 			
		return ctrl.getPlayer(playerId);		
	}	
	

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/list")
	public List<ResumedPlayer> getAllPlayers(){
			
		return ctrl.getAllPlayers();
	}
	
	@POST
	@Produces("application/json; charset=UTF-8")
	@Path("/getPlayer")
	public List<ResumedPlayer> getLeaguePlayers(PlayerFilter filter){ 			
		return ctrl.getPlayerFilter(filter);		
	}		


}