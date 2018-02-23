package br.com.pofexo.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pofexo.model.bean.LeagueMO;
import br.com.pofexo.model.input.League;
import br.com.pofexo.model.repo.LeagueRepo;

@Service
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
	
	public LeagueMO persistLeague(List<League> leagues, League league) {
		
		int i = leagues.indexOf(league);
		if (i > -1) {
			return leagues.get(i).getLeague();			
		} else {			
			LeagueMO lg = leagueDao.save(convertLeagueToMO(league));
			league.setLeague(lg);
			leagues.add(league);
			return lg;
		}
	}
	
	
}
