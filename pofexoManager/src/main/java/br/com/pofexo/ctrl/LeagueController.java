package br.com.pofexo.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pofexo.model.bean.LeagueMO;
import br.com.pofexo.model.repo.LeagueRepo;

@RestController
@RequestMapping("/league")
public class LeagueController {

	@Autowired
	private LeagueRepo leagueDao;

	@CrossOrigin
	@GetMapping("/list")
	public Page<LeagueMO> getLeagues(@PageableDefault(value = 100) Pageable pageable) {
		return leagueDao.findAll(pageable);
	}
	
	@CrossOrigin
	@GetMapping("/getLeague/{league}")
	public LeagueMO getLeague(@PathVariable("league") Integer league) {
		return leagueDao.findOne(league);
				
	}	

}
