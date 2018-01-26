package br.com.ctrl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
import br.com.model.misc.NotificationCore;
import br.com.model.misc.TeamCore;
import br.com.model.repo.BidRepo;
import br.com.model.repo.MarketRepo;
import br.com.model.repo.PlayerRepo;

@RestController
@RequestMapping("/market")
public class MarketController {

	@Autowired
	private BidRepo bidDao;
	@Autowired
	private PlayerRepo plDao;
	@Autowired
	private MarketRepo mkDao;

	@CrossOrigin
	@PostMapping("/placeBid")
	public PlayerMO placeBid(@RequestBody PlayerMO playerBid) {

		TeamCore tmCore = new TeamCore();
		NotificationCore ntCore = new NotificationCore();
		
		if (isMarketClose()) {
			closeMarket(ntCore, tmCore);
			
			playerBid.getBid().setStatus(BidStatus.RESYNC);
			return playerBid;
		}
				
		PlayerMO player = plDao.findOne(playerBid.getId());

		if (player.getBid().getBidValue() > playerBid.getBid().getBidValue()) {

			if (tmCore.haveMoney(playerBid)) {

				if (player.getTeam() != null) {
					tmCore.increaseBudget(player);
					ntCore.setBidSupass(player);
				}

				tmCore.decreaseBudget(playerBid);
				ntCore.setBidSucess(playerBid);

				playerBid.getBid().setBidTime(LocalDateTime.now());

				bidDao.save(playerBid.getBid());
				playerBid = plDao.save(playerBid);

				playerBid.getBid().setStatus(BidStatus.APROVED);
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
		LocalDateTime base = LocalDateTime.now();
		Iterable<MarketMO> mks = mkDao.findAll();
		for (MarketMO market : mks) {
			//System.out.println(market.getCloseTime());
			if (base.isAfter(market.getCloseTime())) {				
				return true;
			}
		}
		return false;
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
	
	@Scheduled(fixedDelay = 100000) //milisec
	public void abc() {
		System.out.println("agora é" + LocalDate.now());
	}
	

}
