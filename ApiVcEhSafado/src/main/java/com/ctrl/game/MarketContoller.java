package com.ctrl.game;

import java.util.ArrayList;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.model.player.BidInfo;
import com.model.player.BidInfoDAO;
import com.model.player.BidInfoFactory;
import com.model.player.PlayerDAO;
import com.model.player.PlayerEntity;
import com.model.team.Team;
import com.model.team.TeamDAO;
import com.model.team.TeamPlayerEntity;

@Path("/market")
public class MarketContoller {

	private final TeamDAO teamAcc = new TeamDAO();	
	private final BidInfoDAO bidDao = new BidInfoDAO();
	
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/placeBid")	
	public BidInfo placeBid(BidInfo bid) {	
		
		Team team = teamAcc.getItem(bid.getTeamID());
		
		
		BidInfo bidBase = bidDao.getItem(bid.getPlayerID());
		BidInfo bidReturn;
		
		if (bid.getBidValue() > bidBase.getBidValue()) {
			if (haveMoney(bid.getBidValue(), team)) {
				teamAcc.decreaseBudget(bid);				
				teamAcc.increaseBudget(bidBase);				
				bidDao.Insert(bid);
				bidReturn = BidInfoFactory.newProtectedBid(bid.getPlayerID(), bid.getBidValue());
				bidReturn.setBidAproved(true);
			} else {
				bidReturn = bid;								
				bidReturn.setBidAproved(false);				
			}
		} else {
			bidReturn = BidInfoFactory.newProtectedBid(bidBase.getPlayerID(), bidBase.getBidValue());		
			bidReturn.setBidAproved(false);				
		}	
		
		return bidReturn;

	}

	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/initialBid")		
	public BidInfo initialBid(BidInfo bid) {
		 
		Team team = teamAcc.getItem(bid.getTeamID());
		
		BidInfo bidBase = bidDao.getItem(bid.getPlayerID());		
		BidInfo bidReturn;
		
		if (bidBase != null) {
			bidReturn = BidInfoFactory.newProtectedBid(bidBase.getPlayerID(), bidBase.getBidValue());			
			bidReturn.setBidAproved(false);
		} else {
			if (haveMoney(bid.getBidValue(), team)) {
				teamAcc.decreaseBudget(bid);			
				bidDao.Insert(bid);
				bidReturn = BidInfoFactory.newProtectedBid(bid.getPlayerID(), bid.getBidValue());
				bidReturn.setBidAproved(true);
			} else {				
				bidReturn = BidInfoFactory.newBid(bid.getPlayerID());
				bidReturn.setBidValue(bidReturn.getOriginalValue());				
				bidReturn.setBidAproved(false);
			}
		}	

		return bidReturn;
	}
	
	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/close")	
	public void closeMarket() {		
		
		BidInfo bid;
		Team team;		
		PlayerDAO playDao = new PlayerDAO();
		TeamPlayerEntity boobs;
		ArrayList<TeamPlayerEntity> players = new ArrayList<TeamPlayerEntity>();
		
		ArrayList<BidInfo> bids = bidDao.getList();		
		Iterator<BidInfo> itBid = bids.iterator();		
				
		while (itBid.hasNext()) {			
			bid = itBid.next();						
			boobs = new TeamPlayerEntity();
			boobs.setIdPlayer(bid.getPlayerID());
			boobs.setIdTeam(bid.getTeamID());
			players.add(boobs);			
		}
		
		
		ArrayList<Team> teams = teamAcc.getList();
		//TODO add para os times os jogadores
		
	}

	public boolean haveMoney(double value, Team team) {
		double remainBudget = team.getBudget();
		return !(value > remainBudget);		
	}

	
}
