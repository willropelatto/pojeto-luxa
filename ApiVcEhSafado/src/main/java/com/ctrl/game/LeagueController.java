package com.ctrl.game;

import java.util.ArrayList;
import java.util.Iterator;

import com.model.dao.LeagueDAO;
import com.model.entity.LeagueEntity;
import com.model.in.League;

public class LeagueController {

	public ArrayList<League> ShowLeagues() {
		
//		LeagueDAO ldao = new LeagueDAO();		
//		ArrayList<League> list = ldao.getItems();
		
		//TODO jogar para rest no futuro
		
		return new ArrayList<League>();
	}
	
	public LeagueEntity converteLeagueJsonToLeagueDB(League league) {		
		LeagueEntity entity = new LeagueEntity();				
		entity.setAbbrName(league.getAbbrName());
		entity.setImgUrl(league.getImgUrl());
		entity.setName(league.getName());
		entity.setOriginalId(league.getId());
		
		return entity;	
	}	
	
	
	public void saveLeagues(ArrayList<League> leagues) {		    
	    LeagueDAO leagueDao = new LeagueDAO();	    
		Iterator<League> itLeague = leagues.iterator();
		
		while (itLeague.hasNext()) {
			League league = itLeague.next();						
			LeagueEntity leagueEn = converteLeagueJsonToLeagueDB(league);
			leagueDao.Save(leagueEn);			
		}		
		
	}
	
}
