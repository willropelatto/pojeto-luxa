package br.com.mkt.ctrl;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarketController {

	/*@Autowired
	private*/
	
	
/*	public BidInfo placeBid(BidInfo bid) {	

		TeamEntity team = teamAcc.getTeam(bid.getTeamID());
		BidEntity bidBase = bidDao.getItem(bid.getPlayerID());
		PlayerEntity plBase = plDao.getPlayer(bid.getPlayerID());
		BidInfo bidReturn;

		if (bid.getBidValue() > bidBase.getBidValue()) {
			if (haveMoney(bid.getBidValue(), team)) {
				teamAcc.decreaseBudget(bid.getTeamID(), bid.getBidValue());				
				teamAcc.increaseBudget(bidBase.getTeamID(), bidBase.getBidValue());
				plBase.setHasBid(true);
				plDao.update(plBase);				
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

	}*/	
	
}
