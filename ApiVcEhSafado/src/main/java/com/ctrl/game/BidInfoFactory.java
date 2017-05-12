package com.ctrl.game;

import com.model.dao.PlayerDAO;
import com.model.entity.PlayerEntity;
import com.model.out.BidInfo;

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

	public static BidInfo newBid(int playerId) {

		PlayerDAO plDao = new PlayerDAO();		
		PlayerEntity pl = plDao.getPlayer(playerId);

		BidInfo bi = new BidInfo();
		bi.setPlayerID(playerId);		
		bi.setOriginalValue(getOriginalValue(pl.getRating()));	

		return bi;
	}

	public static BidInfo newProtectedBid(int playerId, double value) {

		BidInfo bi = newBid(playerId);
		bi.setBidValue((bi.getOriginalValue() * 0.05) + value);

		return bi;

	}

}
