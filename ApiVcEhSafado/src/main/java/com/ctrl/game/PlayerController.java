package com.ctrl.game;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.model.league.LeagueDao;
import com.model.league.LeagueEntity;
import com.model.player.FullPlayer;
import com.model.player.League;
import com.model.player.LeagueDAO;
import com.model.player.Page;
import com.model.player.PlayerEntity;
import com.model.player.PlayerDao;

import java.util.ArrayList;

public class PlayerController {
	
	public Boolean ContainInList(ArrayList<Integer> lista, int id) {
		
		Boolean encontrou = false;
		
		if (lista.size() > 0) {
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i) == id) {
					encontrou = true;
				}
			}
		} 
		
		return encontrou;					
		
	}

	public void UpdateStoredPlayers() {
		
	
		 Client client = ClientBuilder.newClient();
		 WebTarget target = client.target("https://www.easports.com/fifa/ultimate-team/api/fut/item?page=1");
						
	    String json = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
	    
	    Gson gson = new Gson();
	    
	    Page pg = gson.fromJson(json, Page.class);	   
	    
	    ConverterPlayer cp = new ConverterPlayer();
	    ConverterLeague cl = new ConverterLeague();
	    PlayerDao pdao = new PlayerDao();
	    
	    PlayerEntity player;
	    FullPlayer fullPlay;	  
	    
	    ArrayList<League> leagues = new ArrayList<League>();	    

      LeagueDao leagueDao = new LeagueDao();
	    LeagueEntity league;

	    while (pg.getTotalPages() >= pg.getPage()) {
    
	    	for (int i = 0; i < pg.getItems().length; i++) {
	    		
	    		fullPlay = pg.getItems()[i];    		
	    		
	    		if (fullPlay.getPlayerType().equals("rare") || 
	    			fullPlay.getPlayerType().equals("standard")) {	    		
	    			
	    			League league = fullPlay.getLeague();
	    			if (!leagues.contains(league)) {
	    				leagues.add(league);
	    				
	    				LeagueDAO leagueDao = new LeagueDAO();
	    				leagueDao.Save(league);	
	    			}
	    			
	    			player = conversor.FullToDB(fullPlay);
	    			pdao.Save(player);

						
	    		}	    		
	    		
	    	}
	    	
	    	int pageToGo = pg.getPage()+1;	   
	    	
			if (pg.getPage() < pg.getTotalPages()) {	    	
				target = client.target("https://www.easports.com/fifa/ultimate-team/api/fut/item?page="+pageToGo);
//				target = client.target("http://smartwaysolucoes.com/item"+pageToGo+".json");
				json = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
				gson = new Gson();
				pg = gson.fromJson(json, Page.class);	
			}

	    	
	    	
	    }	    
	    
	}
	
	public void UpdateTestMoacir() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://smartwaysolucoes.com/item7.json");
	    String json = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
	    Gson gson = new Gson();
	    Page pg = gson.fromJson(json, Page.class);	   
	    ConverterPlayer cp = new ConverterPlayer();
	    PlayerDao pdao = new PlayerDao();
	    PlayerEntity player;
	    FullPlayer pl;	    
	    for (int i = 0; i < pg.getItems().length; i++) {
	    		
	    	pl = pg.getItems()[i];    		
	    		
	    	if (pl.getPlayerType().equals("rare") || 
	    		pl.getPlayerType().equals("standard")) {
	    	
	    		player = cp.FullToDB(pl);
	    		pdao.Save(player);
	    	}	    		
	    		
	    }
		
	}
}