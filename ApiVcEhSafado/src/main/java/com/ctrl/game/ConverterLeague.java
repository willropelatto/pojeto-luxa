package com.ctrl.game;

import com.model.league.LeagueEntity;
import com.model.player.League;

public class ConverterLeague {
	
	public LeagueEntity FullToDB(League lg) {
		
		LeagueEntity league = new LeagueEntity();				
		league.setAbbrName(lg.getAbbrName());
		league.setImgUrl(lg.getImgUrl());
		league.setName(lg.getName());
		league.setOriginalId(lg.getId());
		
		return league;
	
	}

}
