package br.com.ctrl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

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

import br.com.BidInfoFactory;
import br.com.model.BidInfo;
import br.com.model.BidStatus;
import br.com.model.BidTite;
import br.com.model.BidTiteRepository;
import br.com.model.Market;
import br.com.model.MarketRepository;
import br.com.model.NotificationTite;
import br.com.model.NotificationTiteRepository;
import br.com.model.PlayerTite;
import br.com.model.PlayerTiteRepository;
import br.com.model.TeamTite;
import br.com.model.TeamTiteRepository;

@RestController
@RequestMapping("/market")
public class MarketController {

	@Autowired
	private BidTiteRepository bidDao;
	@Autowired
	private TeamTiteRepository teamDao;
	@Autowired
	private PlayerTiteRepository plDao;
	@Autowired
	private NotificationTiteRepository ntDao;
	@Autowired
	private MarketRepository mkDao;	

	@CrossOrigin
	@PostMapping("/placeBid")
	public BidInfo placeBid(@RequestBody BidTite bid) {
		BidInfo bidReturn;
		
		if (verifyMarket()) {
			bidReturn = new BidInfo(bid);
			bidReturn.setStatus(BidStatus.MARKET_CLOSE);
			return bidReturn;
		}
		
		TeamTite team = teamDao.findOne(bid.getTeamID());
		BidTite bidBase = bidDao.findOneByPlayerId(bid.getPlayerID());
		PlayerTite player = plDao.findOne(bid.getPlayerID());		

		if (bidBase == null) {
			bidReturn = new BidInfo(bid);
		} else {
			if (bid.getBidValue() > bidBase.getBidValue()) {

				if (teamHaveMoney(bid.getBidValue(), team)) {
					team = decreaseBudget(team, bid.getBidValue());
					increaseBudget(teamDao.findOne(bidBase.getTeamID()), bidBase.getBidValue());

					if (!player.isHasBid()) {
						player = updateStatusBid(player);
					}

					NotificationTite ntTite = new NotificationTite();
					ntTite.setTeamId(bidBase.getTeamID());
					ntTite.setPlayerName(player.getName());
					ntTite.setNotification("Seu lance pelo jogador: " + player.getName() + " foi superado.");
					ntDao.save(ntTite);
					bidDao.delete(bidBase);

					bid.setPlayerName(player.getName());
					NotificationTite ntNewBid = new NotificationTite();
					ntNewBid.setTeamId(bid.getTeamID());
					ntNewBid.setPlayerName(player.getName());
					ntNewBid.setNotification("Seu lance pelo jogador: " + player.getName() + " foi realizado com sucesso.");
					ntDao.save(ntNewBid);
					bid.setBidTime(LocalDateTime.now());
					bid = bidDao.save(bid);

					bidReturn = BidInfoFactory.newProtectedBid(player, bid.getBidValue());
					bidReturn.setStatus(BidStatus.APROVED);
				} else {
					bidReturn = new BidInfo(bid);
					bidReturn.setStatus(BidStatus.NO_MONEY);
				}
			} else {
				bidReturn = BidInfoFactory.newProtectedBid(player, bidBase.getBidValue());
				bidReturn.setStatus(BidStatus.RESYNC);
			}
		}

		return bidReturn;
	}

	@CrossOrigin
	@PostMapping("/initialBid")
	public BidInfo initialBid(@RequestBody BidTite bid) {
		BidInfo result;
		
		if (verifyMarket()) {
			result = new BidInfo(bid);
			result.setStatus(BidStatus.MARKET_CLOSE);
			return result;
		}
		
		TeamTite team = teamDao.findOne(bid.getTeamID());
		BidTite bidBase = bidDao.findOneByPlayerId(bid.getPlayerID());
		PlayerTite player = plDao.findOne(bid.getPlayerID());		

		if (bidBase != null) {
			result = new BidInfo(bid);
			result.setStatus(BidStatus.RESYNC);
		} else {
			if (teamHaveMoney(bid.getBidValue(), team)) {
				team = decreaseBudget(team, bid.getBidValue());
				player = updateStatusBid(player);
				bid.setPlayerName(player.getName());
				
				
				NotificationTite ntNewBid = new NotificationTite();
				ntNewBid.setTeamId(bid.getTeamID());
				ntNewBid.setPlayerName(player.getName());
				ntNewBid.setNotification("Seu lance pelo jogador: " + player.getName() + " foi realizado com sucesso.");
				ntDao.save(ntNewBid);
				
				
				bid.setBidTime(LocalDateTime.now());
				bidDao.save(bid);
				
				result = BidInfoFactory.newProtectedBid(player, bid.getBidValue());
				result.setStatus(BidStatus.APROVED);
			} else {
				result = BidInfoFactory.newBid(player);
				result.getBid().setBidValue(result.getBid().getOriginalValue());
				result.setStatus(BidStatus.NO_MONEY);
			}
		}

		return result;
	}

	private boolean verifyMarket() {
		LocalDateTime base = LocalDateTime.now();
		Iterable<Market> mks = mkDao.findAll();
		for (Market market : mks) {
			System.out.println(market.getCloseTime());
			if (base.isAfter(market.getCloseTime())) {
				closeMarket();
				return true;
			}
		}
		return false;
	}

	@CrossOrigin
	@GetMapping("/player/getBid/{player}")
	public BidInfo getBidFromPlayerId(@PathVariable("player") Integer idPlayer) {

		BidTite bid = bidDao.findOneByPlayerId(idPlayer);
		BidInfo result = new BidInfo(bid);

		if (bid == null) {
			result.setStatus(BidStatus.UNSET);
		} else {
			result.setStatus(BidStatus.APROVED);
		}

		return result;
	}

	@CrossOrigin
	@GetMapping("/team/getBid/{team}")
	public Page<BidTite> getBidFromTeamId(@PathVariable("team") Integer idTeam,
			@PageableDefault(value = 25) Pageable pageable) {
		return bidDao.findByTeamId(idTeam, pageable);
	}

	@CrossOrigin
	@GetMapping("/list")
	public Page<BidTite> getBids(@PageableDefault(value = 25) Pageable pageable) {
		return bidDao.findAll(pageable);
	}
	
	@CrossOrigin
	@GetMapping("/open")	
	public void openMarket() {    	
		mkDao.deleteAll();
		
		Random random = new Random();
    	int hour = random.nextInt(3) + 18; //entre 18 as 21
    	int minute = random.nextInt(59);
    	
    	LocalDate dt = LocalDate.now();
    	dt.plusDays(3);    	
    	LocalTime tm = LocalTime.of(hour, minute);   	
    	
//    	dt = LocalDate.now();
//    	tm = LocalTime.of(13, 15);
    	
    	Market mk = new Market();
    	mk.setCupId(1);
    	mk.setCloseTime(LocalDateTime.of(dt, tm));
    	mkDao.save(mk);		
	}

	public void closeMarket() {
		Iterable<TeamTite> teams = teamDao.findAll();
		for (TeamTite team : teams) {
			NotificationTite notification = new NotificationTite();
			notification.setTeamId(team.getId());
			notification.setPlayerName("Teu pai");
			notification.setNotification("O mercado está fechado.");
			ntDao.save(notification);	
			
			Iterable<BidTite> bids = bidDao.findByTeamId(team.getId());
			for (BidTite bid : bids) {			
				PlayerTite player = plDao.findOne(bid.getPlayerID());					
				player.setTeam(team);
				plDao.save(player);		
			}			
		}	
		
		bidDao.deleteAll();		
	}

	private PlayerTite updateStatusBid(PlayerTite player) {
		player.setHasBid(true);
		return plDao.save(player);
	}

	private TeamTite decreaseBudget(TeamTite team, double bidValue) {
		team.setBudget(team.getBudget() - bidValue);
		return teamDao.save(team);
	}

	private TeamTite increaseBudget(TeamTite team, double bidValue) {
		team.setBudget(team.getBudget() + bidValue);
		return teamDao.save(team);
	}

	private boolean teamHaveMoney(double value, TeamTite team) {
		double remainBudget = team.getBudget();
		return !(value > remainBudget);
	}

}
