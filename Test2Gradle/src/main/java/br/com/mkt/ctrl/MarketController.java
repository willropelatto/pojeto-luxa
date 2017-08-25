package br.com.mkt.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mkt.model.BidTite;
import br.com.mkt.model.BidTiteRepository;

@RestController
public class MarketController {

	@Autowired
	private BidTiteRepository bidDao;
	
	
	@CrossOrigin
	@RequestMapping("/market/get")
	public BidTite getbid() {
		return bidDao.findOneByTeamId(1);
	}
	
	/*
	public BidTite placeBid(BidTite bid) {	

		//TeamEntity team = teamAcc.getTeam(bid.getTeamID());
		BidTite bidBase = bidDao.findByPlayerId(getItem(bid.getPlayerID());
		//PlayerEntity plBase = plDao.getPlayer(bid.getPlayerID());
		BidTite bidReturn;

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

	}
	*/
	
	
}
