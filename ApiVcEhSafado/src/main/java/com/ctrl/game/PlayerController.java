package com.ctrl.game;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.model.player.FullPlayer;
import com.model.player.League;
import com.model.player.Page;
import com.model.player.PlayerDAO;
import com.model.player.PlayerEntity;
import com.model.player.ResumedPlayer;

public class PlayerController {


	public ResumedPlayer convertPlayerFullToResumed(FullPlayer full) {

		ResumedPlayer resumed = new ResumedPlayer();

		resumed.setBaseId(full.getBaseId());
		resumed.setId(full.getId());
		resumed.setName(full.getName());
		resumed.setPosition(full.getPosition());
		resumed.setRating(full.getRating());	

		return resumed;
	}

	public PlayerEntity convertPlayerFullToDB(FullPlayer fullpl) {

		PlayerEntity player = new PlayerEntity();
		Integer originalId = Integer.parseInt(fullpl.getId());
		Integer idLeague = new Integer(fullpl.getLeague().getId());

		player.setName(fullpl.getName());
		player.setPosition(fullpl.getPosition());
		player.setBaseId(fullpl.getBaseId());
		player.setRating(fullpl.getRating());
		player.setIdLeague(idLeague); 
		player.setOriginalId(originalId);
		return player;

	}		


	public void UpdateStoredPlayers() {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://www.easports.com/fifa/ultimate-team/api/fut/item?page=1");						
		String json = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);	    
		Gson gson = new Gson();	    
		Page pg = gson.fromJson(json, Page.class);	    

		PlayerDAO playerDao = new PlayerDAO();

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
}