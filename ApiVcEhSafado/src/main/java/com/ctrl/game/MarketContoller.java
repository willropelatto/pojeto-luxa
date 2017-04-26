package com.ctrl.game;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.model.player.BidInfo;
import com.model.player.BidInfoDAO;
import com.model.player.BidInfoFactory;
import com.model.team.Team;
import com.model.team.TeamDAO;

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

	public boolean haveMoney(double value, Team team) {
		double remainBudget = team.getBudget();
		return !(value > remainBudget);		
	}

	
}
