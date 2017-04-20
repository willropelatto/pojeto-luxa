package com.ctrl.game;

import com.model.player.BidInfo;
import com.model.player.BidInfoDAO;
import com.model.player.BidInfoFactory;

public class MarketContoller {

	public BidInfo placeBid(BidInfo bid) {	
		
		BidInfoDAO bidDao = new BidInfoDAO();

		BidInfo bidBase = bidDao.getItem(bid.getPlayerID());
		
		if (bid.getBidValue() > bidBase.getBidValue()) {
			
			bidDao.Insert(bid);			
			
			return BidInfoFactory.newProtectedBid(bid.getPlayerID(), bid.getBidValue());
				
		} else {
			BidInfo bidReturn = BidInfoFactory.newProtectedBid(bidBase.getPlayerID(), bidBase.getBidValue());		
			bidReturn.setTeamID(bid.getTeamID()); //se der ruim devolvo o id do team
		
			return bidReturn;	
		}		
		
	}
	
}
