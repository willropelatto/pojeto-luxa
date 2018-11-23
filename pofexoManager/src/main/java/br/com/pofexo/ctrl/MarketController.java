package br.com.pofexo.ctrl;

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

import br.com.pofexo.model.bean.MarketMO;
import br.com.pofexo.model.bean.PlayerMO;
import br.com.pofexo.model.bean.TeamMO;
import br.com.pofexo.model.misc.BidStatus;
import br.com.pofexo.model.misc.PlayerStatus;
import br.com.pofexo.model.repo.MarketRepo;

@RestController
@RequestMapping("/market")
public class MarketController {

	@Autowired
	private PlayerCore playerCore;
	@Autowired
	private MarketRepo mkDao;
	@Autowired
	private TeamCore tmCore;
	@Autowired
	private NotificationCore ntCore;

	@CrossOrigin
	@PostMapping("/dismiss")
	public PlayerMO dismiss(@RequestBody PlayerMO playerBid) {
		PlayerMO player = playerCore.getPlayer(playerBid.getId());
		if (isMarketClose()) {
			closeMarket(ntCore, tmCore);

			player.getBid().setStatus(BidStatus.MARKET_CLOSE);
			return player;
		}

		TeamMO team = player.getTeam();
		double bidValue = player.getBid().getBidValue();

		if (player.getStatus() == PlayerStatus.CONTRACT) {
			player.setBid(null);
			player.setStatus(PlayerStatus.UNAVAIBLE);
			playerBid = playerCore.persistPlayerBid(player);
			tmCore.increaseBudget(team, bidValue);
			tmCore.persistTeam(team);
		} else
			playerBid = player;

		return playerBid;

	}

	@CrossOrigin
	@PostMapping("/placeBid")
	public PlayerMO placeBid(@RequestBody PlayerMO playerBid) {

		PlayerMO player = playerCore.getPlayer(playerBid.getId());
		if (isMarketClose()) {
			closeMarket(ntCore, tmCore);

			player.getBid().setStatus(BidStatus.MARKET_CLOSE);
			return player;
		}

		// mantido os valores originais, pq depois de gravar no banco o objeto
		// referenciado com o buffer da base é atualizado.
		TeamMO team = player.getTeam();
		double bidValue = player.getBid().getBidValue();

		synchronized (this) {
			if (playerBid.getBid().getBidValue() >= bidValue) {
				if (tmCore.haveMoney(playerBid)) {
					playerBid = playerCore.preparePlayerBid(playerBid, player);
					tmCore.decreaseBudget(playerBid);
					playerBid = playerCore.persistPlayerBid(playerBid);
					
					
					ntCore.setBidSucess(playerBid);
					tmCore.persistTeam(playerBid.getTeam());

					if (team != null) {
						ntCore.setBidSupass(player);
						tmCore.increaseBudget(team, bidValue);
						tmCore.persistTeam(team);
					}
					//playerBid.getBid().setBidValue(playerBid.getBid().getNextValue());
				} else {
					playerBid = player;
					playerBid.getBid().setStatus(BidStatus.NO_MONEY);
				}

			} else {
				playerBid = player;
				playerBid.getBid().setStatus(BidStatus.RESYNC);
			}
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

		// Caso mercado não esteja aberto, considerar fechado.
		return (!aberto);
	}

	@CrossOrigin
	@PostMapping("/open")
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

		playerCore.setContractPlayers();
	}

	@CrossOrigin
	@PostMapping("/setLeagues")
	public long setAvaibleLeagues(@RequestBody Integer[] ids) {
		return playerCore.setAvaiblePlayers(ids);
	}

	/*
	 * @Scheduled(fixedDelay = 100000) //milisec public void abc() {
	 * System.out.println("agora é " + LocalDateTime.now()); }
	 */

}
