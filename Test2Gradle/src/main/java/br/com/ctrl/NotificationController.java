package br.com.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.NotificationTite;
import br.com.model.NotificationTiteRepository;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	private NotificationTiteRepository ntDao;
	
	
	@CrossOrigin	
	@GetMapping("/getByTeam/{team}")
	public Page<NotificationTite> listTeams(@PathVariable("team") Integer team, @PageableDefault(value = 20) Pageable pageable) {
		return ntDao.findByTeamId(team, pageable);		
	}
	
	@CrossOrigin	
	@GetMapping("/getLasts/{team}")
	public Page<NotificationTite> getLastNotifications(@PathVariable("team") Integer team, @PageableDefault(value = 5) Pageable pageable) {
		return ntDao.findByTeamIdOrderByIdDesc(team, pageable);		
	}
	
	
	
}
