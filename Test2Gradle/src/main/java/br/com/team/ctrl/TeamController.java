package br.com.team.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.team.model.TeamTite;
import br.com.team.model.TeamTiteRepository;

@RestController
public class TeamController {

	@Autowired
	private TeamTiteRepository ttDao;
	
	
	@CrossOrigin			
	@RequestMapping("/team/list")
	public Page<TeamTite> listTeams(@PageableDefault(value = 50) Pageable pageable) {
		return ttDao.findAll(pageable);		
	}
	
	@CrossOrigin		
	@RequestMapping("/team/getByUser/{user}")
	public TeamTite getByUser(@PathVariable("user") Integer user) {		
		return ttDao.findOneByIdUser(user);				 
	}	
	
	@CrossOrigin		
	@RequestMapping(value="/team/register", method=RequestMethod.POST)
	public TeamTite register(@RequestBody TeamTite team) {		
		return ttDao.save(team);
	}	
	
}
