package br.com.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.LeagueTite;
import br.com.model.LeagueTiteRepository;

@RestController
@RequestMapping("/league")
public class LeagueController {

	@Autowired
	private LeagueTiteRepository leagueDao;

	@CrossOrigin
	@GetMapping("/list")
	public Page<LeagueTite> getLeagues(@PageableDefault(value = 100) Pageable pageable) {
		return leagueDao.findAll(pageable);
	}

}
