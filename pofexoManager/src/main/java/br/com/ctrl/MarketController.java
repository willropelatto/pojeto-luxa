package br.com.ctrl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.bean.MarketMO;
import br.com.model.bean.PlayerMO;
import br.com.model.bean.TeamMO;
import br.com.model.misc.BidStatus;
import br.com.model.misc.PlayerStatus;
import br.com.model.repo.MarketRepo;
import br.com.model.repo.PlayerRepo;

@RestController
@RequestMapping("/market")
public class MarketController {

	@Autowired
	private PlayerRepo plDao;
	@Autowired
	private MarketRepo mkDao;
	@Autowired
	private TeamCore tmCore;
	@Autowired
	private NotificationCore ntCore;

	@CrossOrigin
	@PostMapping("/placeBid")
	public PlayerMO placeBid(@RequestBody PlayerMO playerBid) {
		
		PlayerMO player = plDao.findOne(playerBid.getId());
		if (isMarketClose()) {
			closeMarket(ntCore, tmCore);
			
			player.getBid().setStatus(BidStatus.MARKET_CLOSE);
			return player;
		}
		
		//mantido os valores originais, pq depois de gravar no banco o objeto referenciado com o buffer da base é atualizado.
		TeamMO team = player.getTeam();
		double bidValue = player.getBid().getBidValue();

		if (playerBid.getBid().getBidValue() > bidValue) {			
			if (tmCore.haveMoney(playerBid)) {	
				playerBid.getBid().setId(player.getBid().getId()); //Manter o mesmo id, pq? boa pergunta...				
				playerBid.setAttributes(player.getAttributes()); //Gambi para não sobrescrever os atributos.			
				playerBid.setLeague(player.getLeague()); //Gambi para não sobrescrever a liga.
				
				tmCore.decreaseBudget(playerBid);				
				playerBid.getBid().setStatus(BidStatus.APROVED);
				playerBid.setStatus(PlayerStatus.ON_BID);
				playerBid = plDao.save(playerBid);				
				
				ntCore.setBidSucess(playerBid);
				tmCore.persistTeam(playerBid.getTeam());
				
				if (team != null) {
					ntCore.setBidSupass(player);
					tmCore.increaseBudget(team, bidValue);
					tmCore.persistTeam(team);					
				}

			} else {
				playerBid = player;
				playerBid.getBid().setStatus(BidStatus.NO_MONEY);
			}

		} else {
			playerBid = player;
			playerBid.getBid().setStatus(BidStatus.RESYNC);
		}

		return playerBid;	
	}

	private boolean isMarketClose() {
		boolean aberto = false;
		LocalDateTime base = LocalDateTime.now();
		Iterable<MarketMO> mks = mkDao.findAll();		
		for (MarketMO market : mks) {
			aberto = true;
			if (base.isAfter(market.getCloseTime())) {				
				return true;
			} 							
		}
		
		//Caso mercado não esteja aberto, considerar fechado.
		return (!aberto);
	}

	@CrossOrigin
	@GetMapping("/open")
	public void openMarket() {
		mkDao.deleteAll();

		Random random = new Random();
		LocalDate dt = LocalDate.now().plusDays(3);
		LocalTime tm = LocalTime.of(random.nextInt(3) + 18, random.nextInt(59));

		// dt = LocalDate.now();
		// tm = LocalTime.of(13, 15);

		MarketMO mk = new MarketMO();
		mk.setCupId(1);
		mk.setCloseTime(LocalDateTime.of(dt, tm));
		mkDao.save(mk);
	}

	public void closeMarket(NotificationCore ntCore, TeamCore tmCore) {		
		Iterable<TeamMO> teams = tmCore.findAll();
		
		for (TeamMO team : teams) 
			ntCore.setMarketClosed(team);	
	}	
	
	/*@Scheduled(fixedDelay = 100000) //milisec
	public void abc() {
		System.out.println("agora é " + LocalDateTime.now());
	}*/
	

}