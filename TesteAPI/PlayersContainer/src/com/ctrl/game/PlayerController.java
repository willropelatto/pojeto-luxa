package com.ctrl.game;

import com.google.gson.Gson;
import com.model.player.FullPlayer;
import com.model.player.Page;
import com.model.player.PlayerDB;
import com.model.player.PlayerDBDao;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class PlayerController {
	
	public void UpdateStoredPlayers() {
		
		Client c = Client.create(); 
		WebResource wr = c.resource("https://www.easports.com/fifa/ultimate-team/api/fut/item?page=1");
	    String json = wr.get(String.class);
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
	    	
			wr = c.resource("https://www.easports.com/fifa/ultimate-team/api/fut/item?page="+pageToGo);
		    json = wr.get(String.class);
		    gson = new Gson();
		    pg = gson.fromJson(json, Page.class);	 	    	
	    	
	    	
	    }	    
	    
	}
}