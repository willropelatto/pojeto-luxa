package com.ctrl.game;

import com.model.player.FullPlayer;
import com.model.player.PlayerEntity;
import com.model.player.ResumedPlayer;

public class ConverterPlayer {
	
	public ResumedPlayer FullToResumed(FullPlayer full) {
	
		ResumedPlayer rp = new ResumedPlayer();

		rp.setBaseId(full.getBaseId());
		rp.setId(full.getId());
		rp.setName(full.getName());
		rp.setPosition(full.getPosition());
		rp.setRating(full.getRating());	
		
		return rp;
	}
	
	public PlayerEntity FullToDB(FullPlayer pl) {
		
		PlayerEntity player = new PlayerEntity();
		
		String name = pl.getFirstName()+ " " + pl.getLastName();
		player.setName(name);
		player.setPosition(pl.getPosition());
		player.setBaseId(pl.getBaseId());
		player.setRating(pl.getRating());
		player.setIdLeague(0); 
		return player;
	
	}

}
