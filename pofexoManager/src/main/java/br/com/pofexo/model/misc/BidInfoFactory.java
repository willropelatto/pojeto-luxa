package br.com.pofexo.model.misc;

import br.com.pofexo.model.bean.BidMO;
import br.com.pofexo.model.bean.PlayerMO;

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

	public static BidMO newBid(PlayerMO player) {
		BidMO bid = new BidMO();
		bid.setOriginalValue(getOriginalValue(player.getRating()));
		//bid.setBidValue(bid.getOriginalValue());
		bid.setStatus(BidStatus.UNSET);

		return bid;
	}
}
