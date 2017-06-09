package com.ctrl.game;

import java.util.ArrayList;
import java.util.List;

import com.model.dao.BidInfoDAO;
import com.model.dao.BidLogDAO;
import com.model.dao.PlayerDAO;
import com.model.dao.TeamDAO;
import com.model.dao.TeamPlayerDAO;
import com.model.entity.BidEntity;
import com.model.entity.BidEntityLog;
import com.model.entity.TeamEntity;
import com.model.entity.TeamPlayerEntity;
import com.model.out.BidInfo;
import com.model.out.ResumedPlayer;

public class MarketContoller {

	private final TeamDAO teamAcc = new TeamDAO();	
	private final BidInfoDAO bidDao = new BidInfoDAO();
	private final BidLogDAO logDao = new BidLogDAO();	

	public final BidInfo convertEntityToInfo(BidEntity bid) {		
		
		BidInfo bidRet = new BidInfo();
		bidRet.setBidValue(bid.getBidValue());
		bidRet.setOriginalValue(bid.getOriginalValue());
		bidRet.setPlayerID(bid.getPlayerID());
		bidRet.setTeamID(bid.getTeamID());
		bidRet.setBidAproved(false);

		return bidRet;
	}
	
	public final BidInfo convertEntityToInfoBid(BidEntity bid) {
		
		PlayerDAO playerDao = new PlayerDAO();	
		PlayerController pl = new PlayerController();
		
		BidInfo bidRet = new BidInfo();
		bidRet.setBidValue(bid.getBidValue());
		bidRet.setOriginalValue(bid.getOriginalValue());
		bidRet.setPlayerID(bid.getPlayerID());
		ResumedPlayer rs = pl.convertPlayerEntityToResumed(playerDao.getPlayer(bid.getPlayerID()));
		bidRet.setPlayerName(rs.getName());
		bidRet.setTeamID(bid.getTeamID());
		bidRet.setBidAproved(false);

		return bidRet;
	}
	
	

	public final BidEntity convertInfoToEntity(BidInfo bid) {

		BidEntity bidRet = new BidEntity();
		bidRet.setBidValue(bid.getBidValue());
		bidRet.setOriginalValue(bid.getOriginalValue());
		bidRet.setPlayerID(bid.getPlayerID());
		bidRet.setTeamID(bid.getTeamID());

		return bidRet;
	}

	public final BidEntityLog convertEntityToLog(BidEntity bid) {

		BidEntityLog bidRet = new BidEntityLog();
		bidRet.setBidValue(bid.getBidValue());
		bidRet.setOriginalValue(bid.getOriginalValue());
		bidRet.setPlayerID(bid.getPlayerID());
		bidRet.setTeamID(bid.getTeamID());

		return bidRet;
	}
	
	public final BidEntityLog convertInfoToLog(BidInfo bid) {

		BidEntityLog bidRet = new BidEntityLog();
		bidRet.setBidValue(bid.getBidValue());
		bidRet.setOriginalValue(bid.getOriginalValue());
		bidRet.setPlayerID(bid.getPlayerID());
		bidRet.setTeamID(bid.getTeamID());

		return bidRet;
	}

	public BidInfo placeBid(BidInfo bid) {	

		TeamEntity team = teamAcc.getTeam(bid.getTeamID());
		BidEntity bidBase = bidDao.getItem(bid.getPlayerID());
		BidInfo bidReturn;

		if (bid.getBidValue() > bidBase.getBidValue()) {
			if (haveMoney(bid.getBidValue(), team)) {
				teamAcc.decreaseBudget(bid.getTeamID(), bid.getBidValue());				
				teamAcc.increaseBudget(bidBase.getTeamID(), bidBase.getBidValue());				
				logDao.save(convertEntityToLog(bidBase));
				bidDao.delete(bidBase);
				bidDao.save(convertInfoToEntity(bid));
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
	
	public BidInfo getBidFromPlayerId(int playerId){
		
		BidInfo bidReturn = null;
		try {
			
			BidEntity bidBase = bidDao.getItem(playerId);
						
			if (bidBase != null){
				bidReturn = convertEntityToInfo(bidBase);
				return bidReturn;
			} 	
		} catch (Exception e) {
			bidReturn = new BidInfo();
		}
		return bidReturn;
	}
	

	
	public List<BidInfo> getAllBids(){		
		List<BidEntity> bids = bidDao.getList();
		List<BidInfo> listBids = new ArrayList<BidInfo>();
		
		for (BidEntity bid : bids) {
			listBids.add(convertEntityToInfo(bid));			
		}
		
		return listBids;
		
		//return bids;
		
	}
	
	public List<BidInfo> getBidsFromTeam(Integer idTeam){		
		List<BidEntity> bids = bidDao.getListTeam(idTeam);
		List<BidInfo> listBids = new ArrayList<BidInfo>();
		
		for (BidEntity bid : bids) {
			listBids.add(convertEntityToInfoBid(bid));			
		}
		
		return listBids;
		
		//return bids;
		
	}





	public BidInfo initialBid(BidInfo bid) {

		TeamEntity team = teamAcc.getTeam(bid.getTeamID());

		BidEntity bidBase = bidDao.getItem(bid.getPlayerID());		
		BidInfo bidReturn;

		if (bidBase != null) {
			bidReturn = BidInfoFactory.newProtectedBid(bidBase.getPlayerID(), bidBase.getBidValue());			
			bidReturn.setBidAproved(false);
		} else {
			if (haveMoney(bid.getBidValue(), team)) {
				teamAcc.decreaseBudget(bid.getTeamID(), bid.getBidValue());					
				logDao.save(convertInfoToLog(bid));
				bidDao.save(convertInfoToEntity(bid));				
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

		TeamPlayerEntity tpEntity;		
		TeamPlayerDAO tpDao = new TeamPlayerDAO(); 
		List<BidEntity> bids = bidDao.getList();		

		for (BidEntity bid : bids) {
			tpEntity = new TeamPlayerEntity();
			tpEntity.setIdPlayer(bid.getPlayerID());
			tpEntity.setIdTeam(bid.getTeamID());
			tpDao.save(tpEntity);	
		}

	}


	public boolean haveMoney(double value, TeamEntity team) {
		double remainBudget = team.getBudget();
		return !(value > remainBudget);		
	}


}
