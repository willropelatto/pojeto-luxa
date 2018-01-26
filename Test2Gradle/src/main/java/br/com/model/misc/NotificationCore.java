package br.com.model.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.model.bean.NotificationMO;
import br.com.model.bean.PlayerMO;
import br.com.model.bean.TeamMO;
import br.com.model.repo.NotificationRepo;

@Controller
public class NotificationCore {
	
	@Autowired
	private NotificationRepo ntDao;

	public void setBidSucess(PlayerMO player) {
		NotificationMO ntf = new NotificationMO();
		ntf.setTeamId(player.getTeam().getId());
		ntf.setPlayerName(player.getName());
		ntf.setNotification("Seu lance pelo jogador: " + player.getName() + " foi realizado com sucesso.");
		ntDao.save(ntf);		
	} 
	
	public void setBidSupass(PlayerMO player) {
		NotificationMO ntf = new NotificationMO();
		ntf.setTeamId(player.getTeam().getId());
		ntf.setPlayerName(player.getName());
		ntf.setNotification("Seu lance pelo jogador: " + player.getName() + " foi superado.");
		ntDao.save(ntf);		
	}
	
	public void setMarketClosed(TeamMO team) {
		NotificationMO notification = new NotificationMO();
		notification.setTeamId(team.getId());
		notification.setPlayerName(team.getName());
		notification.setNotification("Mercado fechado!");
		ntDao.save(notification);
	}
	
	
	
}
