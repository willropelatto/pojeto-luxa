package br.com.pofexo.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import br.com.pofexo.model.bean.NotificationMO;
import br.com.pofexo.model.bean.PlayerMO;
import br.com.pofexo.model.bean.TeamMO;
import br.com.pofexo.model.repo.NotificationRepo;
import br.com.pofexo.model.repo.UserAppRepo;

@Service
public class NotificationCore {
	
	@Autowired
	private NotificationRepo ntDao;
	
	@Autowired
	private UserAppRepo userDao;

	public void setBidSucess(PlayerMO player) {
		NotificationMO ntf = new NotificationMO();
		ntf.setTeamId(player.getTeam().getId());
		ntf.setPlayerName(player.getName());
		ntf.setNotification("Seu lance pelo jogador: " + player.getName() + " foi realizado com sucesso.");
		ntDao.save(ntf);
		
		Message msg = new Message(player, ntf);
		try {
			send(userDao.findOne(player.getTeam().getIdUser()).getUsername(), msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	public void setBidSupass(PlayerMO player) {
		NotificationMO ntf = new NotificationMO();
		ntf.setTeamId(player.getTeam().getId());
		ntf.setPlayerName(player.getName());
		ntf.setNotification("Seu lance pelo jogador: " + player.getName() + " foi superado.");
		ntDao.save(ntf);	
		
		Message msg = new Message(player, ntf);
		try {
			send(userDao.findOne(player.getTeam().getIdUser()).getUsername(), msg);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void setMarketClosed(TeamMO team) {
		NotificationMO notification = new NotificationMO();
		notification.setTeamId(team.getId());
		notification.setPlayerName(team.getName());
		notification.setNotification("Mercado fechado!");
		ntDao.save(notification);
		Message msg = new Message();
		msg.setMessage(notification.getNotification());
		msg.setPlayerName(notification.getPlayerName());
		msg.setTeamId(notification.getTeamId());
		try {
			send(userDao.findOne(team.getIdUser()).getUsername(), msg);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Autowired
	private SimpMessagingTemplate messaging;
	
	public void send(String user, Message message) throws Exception {
		this.messaging.convertAndSendToUser(user, "/notifications/messages", message);
	}
	
}
