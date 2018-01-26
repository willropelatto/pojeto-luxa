package br.com.model.misc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.model.bean.LeagueMO;
import br.com.model.input.League;
import br.com.model.repo.LeagueRepo;

@Controller
public class LeagueCore {

	@Autowired
	private LeagueRepo leagueDao;	
	
	private LeagueMO convertLeagueToMO(League league) {
		LeagueMO entity = new LeagueMO();
		entity.setId(0);
		entity.setAbbrName(league.getAbbrName());
		entity.setImgUrl(league.getImgUrl());
		entity.setName(league.getName());
		entity.setOriginalId(league.getId());

		return entity;
	}		
	
	public LeagueMO persistLeague(ArrayList<League> leagues, League league) {
		
		int i = leagues.indexOf(league);
		if (i > -1) {
			return leagues.get(i).getLeague();			
		} else {			
			LeagueMO lg = leagueDao.save(convertLeagueToMO(league));
			league.setLeague(lg);
			leagues.add(league);
			return lg;
		}

		/*
		if (!leagues.contains(league)) {			
			leagues.add(league);
			return leagueDao.save(convertLeagueToMO(league)); 
		}
		
		return leagueDao.findOneByOriginalId(league.getId());
		*/
	}
	
	
}
