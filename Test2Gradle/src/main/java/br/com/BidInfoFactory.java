package br.com;

import br.com.model.BidInfo;
import br.com.model.BidTite;
import br.com.model.PlayerTite;

public class BidInfoFactory {

	public static double getOriginalValue(int rating) {

		if (rating > 90) {
			return 5000;
		} else 
			if (rating > 85) {
				return 2500;
			} else
				if (rating > 80) {
					return 1500;
				} else
					if (rating > 75) {
						return 600;
					} else
						if (rating > 70) {
							return 500;
						} else
							if (rating > 60) {
								return 300;
							} else {
								return 200;
							}	

	}

	public static BidInfo newBid(PlayerTite player) {
		BidTite bid = new BidTite();
		bid.setPlayerID(player.getId());		
		bid.setOriginalValue(getOriginalValue(player.getRating()));	

		return new BidInfo(bid);
	}

	public static BidInfo newProtectedBid(PlayerTite player, double value) {
		BidInfo bid = newBid(player);
		bid.getBid().setBidValue((bid.getBid().getOriginalValue() * 0.05) + value);

		return bid;
	}

}
