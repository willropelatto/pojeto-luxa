package com.profexo.controller.game;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.profexo.model.player.FullPlayer;
import com.profexo.model.player.Page;
/*import com.profexo.model.player.PlayerDB;
import com.profexo.model.player.PlayerDBDao;*/

public class PlayerController {
	
	public void UpdateStoredPlayers() {
		
	
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://www.easports.com/fifa/ultimate-team/api/fut/item?page=1"); 
				
	    String json = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
	    
	    Gson gson = new Gson();
	    
	    Page pg = gson.fromJson(json, Page.class);	   
	    
	    ConverterPlayer cp = new ConverterPlayer();
	    
	    PlayerDBDao pdao = new PlayerDBDao();
	    PlayerDB player;
	    FullPlayer pl;	    
	    
	    while (pg.getTotalPages() != pg.getPage()) {
	    
	    	for (int i = 0; i < pg.getItems().length; i++) {
	    		
	    		pl = pg.getItems()[i];    		
	    		
	    		if (pl.getPlayerType().equals("rare") || 
	    			pl.getPlayerType().equals("standard")) {
	    		
	    			player = cp.FullToDB(pl);
	    			pdao.Insert(player);
	    		}	    		
	    		
	    	}
	    	
	    	int pageToGo = pg.getPage()+1;
	    	
			target = client.target("https://www.easports.com/fifa/ultimate-team/api/fut/item?page="+pageToGo); 
			json = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		    gson = new Gson();
		    pg = gson.fromJson(json, Page.class);	 	    	
	    	
	    	
	    }	    
	    
	}
}