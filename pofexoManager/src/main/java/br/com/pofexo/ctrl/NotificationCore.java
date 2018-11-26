package br.com.pofexo.ctrl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import br.com.pofexo.model.bean.NotificationMO;
import br.com.pofexo.model.bean.PlayerMO;
import br.com.pofexo.model.bean.TeamMO;
import br.com.pofexo.model.repo.NotificationRepo;

@Service
public class NotificationCore {
	
	@Autowired
	private NotificationRepo ntDao;

	public void setBidSucess(PlayerMO player) {
		NotificationMO ntf = new NotificationMO();
		ntf.setTeamId(player.getTeam().getId());
		ntf.setPlayerName(player.getName());
		ntf.setNotification("Seu lance pelo jogador: " + player.getName() + " foi realizado com sucesso.");
		ntDao.save(ntf);
		
		Message msg = new Message(player, ntf);
		try {
			send(msg);
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
			send(msg);
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
			send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@MessageMapping("/notifications")
	@SendTo("/pofexo/messages")
	public JSONObject send(Message message) throws Exception {
		return new JSONObject(message);
	}
	
}
