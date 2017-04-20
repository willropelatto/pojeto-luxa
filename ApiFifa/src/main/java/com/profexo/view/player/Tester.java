package com.profexo.view.player;

import com.profexo.controller.game.PlayerController;
import com.google.gson.Gson;
import com.profexo.model.player.Page;
import org.glassfish.jersey.client.*;


//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.WebResource;

public class Tester {

	public static void main(String[] args) {

		PlayerController pc = new PlayerController();
		pc.UpdateStoredPlayers();
		System.out.println("terminei");
		
//		Client c = Client.create();  https://www.easports.com/uk/fifa/ultimate-team/api/fut/item?jsonParamObject=%7b%22playerType%22:%rare%22%7d
//		WebResource wr = c.resource("https://www.easports.com/fifa/ultimate-team/api/fut/item?page=1");
//	    String json = wr.get(String.class);
//	    Gson gson = new Gson();
//	    Page data = gson.fromJson(json, Page.class);
//	    System.out.println(data);
	    
	    //System.out.println(gson.fromJson(json, new TypeToken<List<Player>>(){}.getType()));
	    //http://cdn.content.easports.com/fifa/fltOnlineAssets/CC8267B6-0817-4842-BB6A-A20F88B05418/2017/fut/items/web/players.json
	}

}
