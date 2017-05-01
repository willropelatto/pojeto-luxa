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
import com.model.team.TeamPlayerDAO;
import com.model.team.TeamPlayerEntity;

public class MarketContoller {

	private final TeamDAO teamAcc = new TeamDAO();	
	private final BidInfoDAO bidDao = new BidInfoDAO();


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


	public void closeMarket() {		

		BidInfo bid;
		TeamPlayerEntity boobs;		
		TeamPlayerDAO tpDao = new TeamPlayerDAO(); 

		ArrayList<BidInfo> bids = bidDao.getList();		
		Iterator<BidInfo> itBid = bids.iterator();		

		while (itBid.hasNext()) {			
			bid = itBid.next();						
			boobs = new TeamPlayerEntity();
			boobs.setIdPlayer(bid.getPlayerID());
			boobs.setIdTeam(bid.getTeamID());
			tpDao.save(boobs);								
		}	

	}


	public boolean haveMoney(double value, Team team) {
		double remainBudget = team.getBudget();
		return !(value > remainBudget);		
	}


}
