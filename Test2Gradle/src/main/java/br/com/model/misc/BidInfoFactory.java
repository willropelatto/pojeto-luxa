package br.com.model.misc;

import br.com.model.bean.BidMO;
import br.com.model.bean.PlayerMO;

public class BidInfoFactory {

	private static double getOriginalValue(int rating) {

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

	public static BidInfo newBid(PlayerMO player) {
		BidMO bid = new BidMO();
		bid.setPlayerID(player.getId());		
		bid.setOriginalValue(getOriginalValue(player.getRating()));	

		return new BidInfo(bid);
	}

	public static BidInfo newProtectedBid(PlayerMO player, double value) {
		BidInfo bid = newBid(player);
		bid.getBid().setBidValue((bid.getBid().getOriginalValue() * 0.05) + value);

		return bid;
	}

}