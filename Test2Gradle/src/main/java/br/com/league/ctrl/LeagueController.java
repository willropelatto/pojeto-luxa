package br.com.league.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.league.model.LeagueTite;
import br.com.league.model.LeagueTiteRepository;

@RestController
public class LeagueController {
	
	@Autowired
	private LeagueTiteRepository leagueDao;	
	
	@CrossOrigin	
	@RequestMapping("/league/list")
	public Page<LeagueTite> getLeagues(@PageableDefault(value = 50) Pageable pageable) {		
		return leagueDao.findAll(pageable);
	}
	
	

}
