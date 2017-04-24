package com.ctrl.game;

import com.model.player.BidInfo;
import com.model.player.BidInfoDAO;
import com.model.player.BidInfoFactory;
import com.model.team.Team;
import com.model.team.TeamDAO;

public class MarketContoller {

	public BidInfo placeBid(BidInfo bid) {	
		TeamDAO tdao = new TeamDAO(); 
		Team team = tdao.getItem(bid.getTeamID());
		
		BidInfoDAO bidDao = new BidInfoDAO();
		BidInfo bidBase = bidDao.getItem(bid.getPlayerID());
		BidInfo bidReturn;
		
		if (bid.getBidValue() > bidBase.getBidValue()) {
			if (haveMoney(bid.getBidValue(), team)) {
				tdao.decreaseBudget(bid);				
				tdao.increaseBudget(bidBase);				
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

	public BidInfo initialBid(BidInfo bid) {
		TeamDAO tdao = new TeamDAO(); 
		Team team = tdao.getItem(bid.getTeamID());

		BidInfoDAO bidDao = new BidInfoDAO();
		BidInfo bidBase = bidDao.getItem(bid.getPlayerID());		
		BidInfo bidReturn;
		
		if (bidBase != null) {
			bidReturn = BidInfoFactory.newProtectedBid(bidBase.getPlayerID(), bidBase.getBidValue());			
			bidReturn.setBidAproved(false);
		} else {
			if (haveMoney(bid.getBidValue(), team)) {
				tdao.decreaseBudget(bid);			
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
