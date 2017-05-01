package com.view.game;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ctrl.game.MarketContoller;
import com.model.out.BidInfo;

@Path("/market")
public class MarketRest {

	private final MarketContoller mkControl = new MarketContoller();	


	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/placeBid")	
	public BidInfo placeBid(BidInfo bid) {		

		return mkControl.placeBid(bid);

	}

	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/initialBid")		
	public BidInfo initialBid(BidInfo bid) {

		return mkControl.initialBid(bid);
	}

	@POST	
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/close")	
	public void closeMarket() {		

		mkControl.closeMarket();
	}
}
