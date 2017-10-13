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

import br.com.model.bean.BidMO;
import br.com.model.bean.MarketMO;
import br.com.model.bean.NotificationMO;
import br.com.model.bean.PlayerMO;
import br.com.model.bean.TeamMO;
import br.com.model.misc.BidInfo;
import br.com.model.misc.BidInfoFactory;
import br.com.model.misc.BidStatus;
import br.com.model.repo.BidTiteRepository;
import br.com.model.repo.MarketRepository;
import br.com.model.repo.NotificationTiteRepository;
import br.com.model.repo.PlayerTiteRepository;
import br.com.model.repo.TeamTiteRepository;

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
	public BidInfo placeBid(@RequestBody BidMO bid) {
		BidInfo bidReturn;
		
		if (verifyMarket()) {
			bidReturn = new BidInfo(bid);
			bidReturn.setStatus(BidStatus.MARKET_CLOSE);
			return bidReturn;
		}
		
		TeamMO team = teamDao.findOne(bid.getTeamID());
		BidMO bidBase = bidDao.findOneByPlayerId(bid.getPlayerID());
		PlayerMO player = plDao.findOne(bid.getPlayerID());		

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

					NotificationMO ntTite = new NotificationMO();
					ntTite.setTeamId(bidBase.getTeamID());
					ntTite.setPlayerName(player.getName());
					ntTite.setNotification("Seu lance pelo jogador: " + player.getName() + " foi superado.");
					ntDao.save(ntTite);
					bidDao.delete(bidBase);

					bid.setPlayerName(player.getName());
					NotificationMO ntNewBid = new NotificationMO();
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
	public BidInfo initialBid(@RequestBody BidMO bid) {
		BidInfo result;
		
		if (verifyMarket()) {
			result = new BidInfo(bid);
			result.setStatus(BidStatus.MARKET_CLOSE);
			return result;
		}
		
		TeamMO team = teamDao.findOne(bid.getTeamID());
		BidMO bidBase = bidDao.findOneByPlayerId(bid.getPlayerID());
		PlayerMO player = plDao.findOne(bid.getPlayerID());		

		if (bidBase != null) {
			result = new BidInfo(bid);
			result.setStatus(BidStatus.RESYNC);
		} else {
			if (teamHaveMoney(bid.getBidValue(), team)) {
				team = decreaseBudget(team, bid.getBidValue());
				player = updateStatusBid(player);
				bid.setPlayerName(player.getName());
				
				
				NotificationMO ntNewBid = new NotificationMO();
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
		Iterable<MarketMO> mks = mkDao.findAll();
		for (MarketMO market : mks) {
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

		BidMO bid = bidDao.findOneByPlayerId(idPlayer);
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
	public Page<BidMO> getBidFromTeamId(@PathVariable("team") Integer idTeam,
			@PageableDefault(value = 25) Pageable pageable) {
		return bidDao.findByTeamId(idTeam, pageable);
	}

	@CrossOrigin
	@GetMapping("/list")
	public Page<BidMO> getBids(@PageableDefault(value = 25) Pageable pageable) {
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
    	
    	dt = LocalDate.now();
    	tm = LocalTime.of(13, 15);
    	
    	MarketMO mk = new MarketMO();
    	mk.setCupId(1);
    	mk.setCloseTime(LocalDateTime.of(dt, tm));
    	mkDao.save(mk);		
	}

	public void closeMarket() {
		Iterable<TeamMO> teams = teamDao.findAll();
		for (TeamMO team : teams) {
			NotificationMO notification = new NotificationMO();
			notification.setTeamId(team.getId());
			notification.setPlayerName("Teu pai");
			notification.setNotification("O mercado está fechado.");
			ntDao.save(notification);	
			
			Iterable<BidMO> bids = bidDao.findByTeamId(team.getId());
			for (BidMO bid : bids) {			
				PlayerMO player = plDao.findOne(bid.getPlayerID());					
				player.setTeam(team);
				plDao.save(player);		
			}			
		}	
		
		bidDao.deleteAll();		
	}

	private PlayerMO updateStatusBid(PlayerMO player) {
		player.setHasBid(true);
		return plDao.save(player);
	}

	private TeamMO decreaseBudget(TeamMO team, double bidValue) {
		team.setBudget(team.getBudget() - bidValue);
		return teamDao.save(team);
	}

	private TeamMO increaseBudget(TeamMO team, double bidValue) {
		team.setBudget(team.getBudget() + bidValue);
		return teamDao.save(team);
	}

	private boolean teamHaveMoney(double value, TeamMO team) {
		double remainBudget = team.getBudget();
		return !(value > remainBudget);
	}

}
