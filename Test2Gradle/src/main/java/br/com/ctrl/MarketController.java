package br.com.ctrl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.scheduling.annotation.Scheduled;
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

	@CrossOrigin
	@PostMapping("/placeBid")
	public BidInfo placeBid(@RequestBody BidTite bid) {

		TeamTite team = teamDao.findOne(bid.getTeamID());
		BidTite bidBase = bidDao.findOneByPlayerId(bid.getPlayerID());
		PlayerTite player = plDao.findOne(bid.getPlayerID());
		BidInfo bidReturn;

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

		TeamTite team = teamDao.findOne(bid.getTeamID());
		BidTite bidBase = bidDao.findOneByPlayerId(bid.getPlayerID());
		PlayerTite player = plDao.findOne(bid.getPlayerID());
		BidInfo bidReturn;

		if (bidBase != null) {
			bidReturn = new BidInfo(bid);
			bidReturn.setStatus(BidStatus.RESYNC);
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
				bidReturn = BidInfoFactory.newProtectedBid(player, bid.getBidValue());
				bidReturn.setStatus(BidStatus.APROVED);
			} else {
				bidReturn = BidInfoFactory.newBid(player);
				bidReturn.getBid().setBidValue(bidReturn.getBid().getOriginalValue());
				bidReturn.setStatus(BidStatus.NO_MONEY);
			}
		}

		return bidReturn;
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
	
    @Scheduled(fixedRate = 600000) //10 minutos
    public void reportCurrentTime() {        
    	System.out.println("The time is now " + LocalDateTime.now());
    	Random random = new Random();
//        return random.nextInt((maximo - minimo) + 1) + minimo;
    }	

	private void closeMarket() {
		Iterable<TeamTite> teams = teamDao.findAll();
		for (TeamTite team : teams) {
			NotificationTite notification = new NotificationTite();
			notification.setTeamId(team.getId());
			notification.setPlayerName("Teu pai");
			notification.setNotification("O mercado está fechado.");
			ntDao.save(notification);			
		}
		
		Iterable<BidTite> bids = bidDao.findAll(new Sort(Sort.Direction.ASC, "teamId"));		
		int teamid = 0;
		List<PlayerTite> pls = new ArrayList<PlayerTite>();
		
		for (BidTite bid : bids) {			
			if (teamid==0) {
				teamid = bid.getTeamID();
			}
			
			PlayerTite player = plDao.findOne(bid.getPlayerID());
			
			if (teamid != bid.getTeamID()) {			
				TeamTite team = teamDao.findOne(bid.getTeamID());
				team.setPlayers(pls);
				teamDao.save(team);
				
				pls.clear();
			} 
			
			pls.add(player);			
		}

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
