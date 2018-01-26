package br.com.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.bean.TeamMO;
import br.com.model.repo.TeamRepo;

@RestController
@RequestMapping("/team")
public class TeamController {

	@Autowired
	private TeamRepo ttDao;
	
	
	@CrossOrigin			
	@GetMapping("/list")
	public Page<TeamMO> listTeams(@PageableDefault(value = 50) Pageable pageable) {
		return ttDao.findAll(pageable);		
	}
	
	@CrossOrigin		
	@GetMapping("/getByUser/{user}")
	public TeamMO getByUser(@PathVariable("user") Integer user) {		
		return ttDao.findOneByIdUser(user);				 
	}	
	
	@CrossOrigin		
	@PostMapping("/register")	
	public TeamMO register(@RequestBody TeamMO team) {		
		return ttDao.save(team);
	}	
	
}
