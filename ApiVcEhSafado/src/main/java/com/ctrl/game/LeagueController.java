package com.ctrl.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.model.dao.LeagueDAO;
import com.model.entity.LeagueEntity;
import com.model.in.League;

public class LeagueController {

	public ArrayList<League> showLeagues() {
		
		LeagueDAO ldao = new LeagueDAO();		
		ArrayList<League> ret = new ArrayList<League>();
		List<LeagueEntity> list = ldao.getLeagues();		
		
		for (LeagueEntity lgEntity : list) {
			ret.add(convertEntityToLeague(lgEntity));
		}
		
		return ret;
	}
	
	public LeagueEntity converteLeagueJsonToLeagueDB(League league) {		
		LeagueEntity entity = new LeagueEntity();				
		entity.setAbbrName(league.getAbbrName());
		entity.setImgUrl(league.getImgUrl());
		entity.setName(league.getName());
		entity.setOriginalId(league.getId());
		
		return entity;	
	}	
	
	public League convertEntityToLeague(LeagueEntity entity) {		
		League league = new League();				
		league.setAbbrName(entity.getAbbrName());
		league.setImgUrl(entity.getImgUrl());
		league.setName(entity.getName());
		league.setId(entity.getOriginalId());
		
		return league;	
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
