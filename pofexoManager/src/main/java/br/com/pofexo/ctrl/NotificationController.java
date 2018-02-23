package br.com.pofexo.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pofexo.model.bean.NotificationMO;
import br.com.pofexo.model.repo.NotificationRepo;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	private NotificationRepo ntDao;
	
	
	@CrossOrigin	
	@GetMapping("/getByTeam/{team}")
	public Page<NotificationMO> listTeams(@PathVariable("team") Integer team, @PageableDefault(value = 50) Pageable pageable) {
		return ntDao.findByTeamIdAndRead(team, false, pageable);		
	}
	
	@CrossOrigin	
	@GetMapping("/getLasts/{team}")
	public Page<NotificationMO> getLastNotifications(@PathVariable("team") Integer team, @PageableDefault(value = 5) Pageable pageable) {
		return ntDao.findByTeamIdAndReadOrderByIdDesc(team, false, pageable);		
	}
	
	@CrossOrigin
	@GetMapping("/markAsRead/{notification}")
	public int markAsRead(@PathVariable("notification") Integer notification) {
		NotificationMO nt = ntDao.findOne(notification);
		nt.setRead(true);	
		ntDao.save(nt);
		return 1;
	}
	
	@CrossOrigin	
	@GetMapping("/markAllAsRead/{team}")
	public int markAllAsRead(@PathVariable("team") Integer team) {
		List<NotificationMO> nts = ntDao.findByTeamIdAndRead(team, false);

		for (NotificationMO nt : nts) {
			nt.setRead(true);			
		}
		
		ntDao.save(nts);		
		return 1;		
	}	
	
}
