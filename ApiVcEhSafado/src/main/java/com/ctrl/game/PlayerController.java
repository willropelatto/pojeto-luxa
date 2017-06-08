package com.ctrl.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.model.dao.PlayerDAO;
import com.model.dao.TeamPlayerDAO;
import com.model.entity.PlayerEntity;
import com.model.entity.TeamPlayerEntity;
import com.model.in.FullPlayer;
import com.model.in.League;
import com.model.in.Page;
import com.model.in.PlayerFilter;
import com.model.out.PlayerList;
import com.model.out.ResumedPlayer;

public class PlayerController {

	private final PlayerDAO playerDao = new PlayerDAO();
	


	public ArrayList<ResumedPlayer> convertListEntityToResumed(List<PlayerEntity> players) {
		
		Iterator<PlayerEntity> itPlayer = players.iterator();
		ArrayList<ResumedPlayer> rsPlayers = new ArrayList<ResumedPlayer>();
		
		while (itPlayer.hasNext()) {
			PlayerEntity enPlayer = itPlayer.next();						
			ResumedPlayer rsPlayer = convertPlayerEntityToResumed(enPlayer);
			rsPlayers.add(rsPlayer);
		}
		
		return rsPlayers;
	}
	
	public ResumedPlayer convertPlayerFullToResumed(FullPlayer full) {

		ResumedPlayer resumed = new ResumedPlayer();

		resumed.setBaseId(full.getBaseId());
		resumed.setId(full.getId());
		resumed.setName(full.getName());
		resumed.setPosition(full.getPosition());
		resumed.setRating(full.getRating());	

		return resumed;
	}
	
	public ResumedPlayer convertPlayerEntityToResumed(PlayerEntity entity) {

		ResumedPlayer resumed = new ResumedPlayer();

		resumed.setBaseId(entity.getBaseId());
		resumed.setId(entity.getId().toString());
		resumed.setName(entity.getName());
		resumed.setPosition(entity.getPosition());
		resumed.setRating(entity.getRating());	

		return resumed;
	}

	public PlayerEntity convertPlayerFullToDB(FullPlayer fullpl) {

		PlayerEntity player = new PlayerEntity();
		Integer idLeague = new Integer(fullpl.getLeague().getId());

		player.setName(fullpl.getName());
		player.setPosition(fullpl.getPosition());
		player.setBaseId(fullpl.getBaseId());
		player.setRating(fullpl.getRating());
		player.setIdLeague(idLeague); 
		player.setOriginalId(fullpl.getId());
		return player;

	}		


	public void UpdateStoredPlayers() {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://www.easports.com/fifa/ultimate-team/api/fut/item?page=1");						
		String json = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);	    
		Gson gson = new Gson();	    
		Page pg = gson.fromJson(json, Page.class);		

		PlayerEntity player;
		FullPlayer fullPlay;	  

		ArrayList<League> leagues = new ArrayList<League>();	    

		while (pg.getTotalPages() >= pg.getPage()) {

			for (int i = 0; i < pg.getItems().length; i++) {

				fullPlay = pg.getItems()[i];    		

				if ((fullPlay.getPlayerType().equals("rare") || fullPlay.getPlayerType().equals("standard")) &&
						!fullPlay.getColor().isEmpty())	{	    		

					League league = fullPlay.getLeague();
					if (!leagues.contains(league)) {
						leagues.add(league);
					}

					player = convertPlayerFullToDB(fullPlay);
					playerDao.Save(player);						
				}	    		

			}

			int pageToGo = pg.getPage()+1;	   

			if (pg.getPage() < pg.getTotalPages()) {	    	
				target = client.target("https://www.easports.com/fifa/ultimate-team/api/fut/item?page="+pageToGo);
				json = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
				gson = new Gson();
				pg = gson.fromJson(json, Page.class);	
			}	    	

		}

		LeagueController lgCtrl = new LeagueController();
		lgCtrl.saveLeagues(leagues);

	}	
	
	public List<ResumedPlayer> getLeaguePlayers(int leagueId) {		 
		
		List<PlayerEntity> enPlayers = playerDao.getPlayerFromLeague(leagueId);				
		return convertListEntityToResumed(enPlayers);
		
	}	
	
	public List<ResumedPlayer> getAllPlayers() {
		
		List<PlayerEntity> enPlayers = playerDao.getAllPlayers();				
		return convertListEntityToResumed(enPlayers);
		
	}
	
	public List<ResumedPlayer> getPlayerFilter(PlayerFilter filter) {
		
		List<PlayerEntity> enPlayers = playerDao.getPlayers(filter);				
		return convertListEntityToResumed(enPlayers);
	}
	
		
	public ArrayList<ResumedPlayer> loadTeamPlayers(int teamId) {
		
		TeamPlayerDAO tpDao = new TeamPlayerDAO();	
		List<TeamPlayerEntity> players = tpDao.getPlayers(teamId);
		ArrayList<ResumedPlayer> ret = new ArrayList<ResumedPlayer>();
		
		for (TeamPlayerEntity teamPlayerEntity : players) {
		
			PlayerEntity playerEnt = playerDao.getPlayer(teamPlayerEntity.getIdPlayer());			
			ResumedPlayer playerRes = this.convertPlayerEntityToResumed(playerEnt);
			ret.add(playerRes);	
			
		}
	
		return ret;		
	}
}