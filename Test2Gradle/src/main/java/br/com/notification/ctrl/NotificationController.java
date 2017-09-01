package br.com.notification.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.notification.model.NotificationTite;
import br.com.notification.model.NotificationTiteRepository;

@RestController
public class NotificationController {

	@Autowired
	private NotificationTiteRepository ntDao;
	
	
	@CrossOrigin			
	@RequestMapping("/notification/getByTeam/{team}")
	public Page<NotificationTite> listTeams(@PathVariable("team") Integer team, @PageableDefault(value = 50) Pageable pageable) {
		return ntDao.findByTeamId(team, pageable);		
	}
	
	@CrossOrigin			
	@RequestMapping("/notification/getLasts/{team}")
	public Page<NotificationTite> getLastNotifications(@PathVariable("team") Integer team, @PageableDefault(value = 5) Pageable pageable) {
		return ntDao.findByTeamIdOrderByIdDesc(team, pageable);		
	}
	
	
	
}
